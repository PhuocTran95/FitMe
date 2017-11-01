package com.phuoctran.fitme.Fragments.SizeListFragments;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.phuoctran.fitme.Databases.Db;
import com.phuoctran.fitme.Models.Person;
import com.phuoctran.fitme.R;
import com.theartofdev.edmodo.cropper.CropImage;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.app.Activity.RESULT_OK;
import static com.phuoctran.fitme.Databases.Db.db;

/**
 * Created by VN-PC on 2017/05/30.
 */

public class AddEditPerson extends Fragment
{
    ImageButton btnCancel;
    ImageButton btnCheck;
    CircleImageView imgAvatar;
    CircleImageView imgAvatarButton;
    EditText txtName;
    EditText txtNote;
    Spinner spinnerGender;
    GenderAdapter adapter;
    Person person;

    private boolean isEdit;
    private boolean isUseDefaultAvatar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_size_list_add_edit_person, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        mappingControl();
        checkEdit();
    }

    private void checkEdit()
    {
        try
        {
            person = new Person();
            int id = getArguments().getInt("person_id", -1);
            isEdit = id == -1 ? false : true;
            if (isEdit)
            {
                try
                {
                    Cursor cursor = db.GetData("Select * from Person where person_id=" + id);
                    cursor.moveToNext();
                    person.setPerson_id(cursor.getInt(0));
                    person.setPerson_name(cursor.getString(1));
                    person.setPerson_role(cursor.getInt(2));
                    person.setPerson_avatar(cursor.getBlob(3));
                    person.setPerson_about(cursor.getString(4));

                    if (person.getPerson_avatar() == null)
                    {
                        imgAvatar.setImageDrawable(getResources().getDrawable(R.drawable.face));
                        isUseDefaultAvatar = true;
                    } else
                    {
                        Bitmap bmp = BitmapFactory.decodeByteArray(person.getPerson_avatar(), 0, person.getPerson_avatar().length);
                        imgAvatar.setImageBitmap(bmp);
                        isUseDefaultAvatar = false;
                    }
                    txtName.setText(person.getPerson_name());
                    txtNote.setText(person.getPerson_about());
                    spinnerGender.setSelection(person.getPerson_role()-1);
                } catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
            isEdit=false;
            isUseDefaultAvatar = true;
        }
    }

    private void mappingControl()
    {
        btnCancel = (ImageButton) getActivity().findViewById(R.id.btn_add_edit_person_cancel);
        btnCheck = (ImageButton) getActivity().findViewById(R.id.btn_add_edit_person_check);
        imgAvatar = (CircleImageView) getActivity().findViewById(R.id.img_add_edit_avatar);
        imgAvatarButton = (CircleImageView) getActivity().findViewById(R.id.img_add_edit_avatar_button);
        txtName = (EditText) getActivity().findViewById(R.id.txt_add_edit_name);
        txtNote = (EditText) getActivity().findViewById(R.id.txt_add_edit_note);
        spinnerGender = (Spinner) getActivity().findViewById(R.id.spinner_add_edit_person);


        String[] lstGender = {getActivity().getString(R.string.male), getActivity().getString(R.string.female), getActivity().getString(R.string.boy), getActivity().getString(R.string.girl)};
        adapter = new GenderAdapter(getActivity(), lstGender);
        spinnerGender.setAdapter(adapter);

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().onBackPressed();
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                saveChanges();
            }
        });

        imgAvatarButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                showAvatarOptionMenu();
            }
        });

        InputFilter[] filterArray = new InputFilter[1];
        filterArray[0] = new InputFilter.LengthFilter(200);
        txtNote.setFilters(filterArray);
    }

    private void showAvatarOptionMenu()
    {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        String[] lstOption = {getString(R.string.option_choose_photo), getString(R.string.option_remove_picture)};
        dialog.setItems(lstOption, new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                switch (which)
                {
                    case 0:
                        choosePhoto();
                        break;
                    case 1:
                        removePhoto();
                        break;
                }
            }
        });
        dialog.show();
    }

    private void removePhoto()
    {
        isUseDefaultAvatar = true;
        imgAvatar.setImageDrawable(getResources().getDrawable(R.drawable.face));
    }

    private void choosePhoto()
    {
        try
        {
            CropImage.activity(null).setAspectRatio(16, 9).start(getContext(), this);
        } catch (Exception ex)
        {
            ex.printStackTrace();
            Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
        }
    }

    private void saveChanges()
    {
        person.setPerson_name(txtName.getText().toString());
        if(person.getPerson_name().trim().length()==0)
        {
            Toast.makeText(getActivity(), getString(R.string.fill_name), Toast.LENGTH_SHORT).show();
            return;
        }

        person.setPerson_about(txtNote.getText().toString());
        person.setPerson_role(spinnerGender.getSelectedItemPosition() + 1);
        if (!isUseDefaultAvatar)
            person.setPerson_avatar(getAvatar(imgAvatar.getDrawable()));
        else
            person.setPerson_avatar(null);
        if (isEdit)
        {
            updatePerson(person);
        } else
        {
            createNewPerson(person);
        }
    }

    private void createNewPerson(Person person)
    {
        try
        {
            person.setPerson_id(getNewPersonID());
            ContentValues contentValues=new ContentValues();
            contentValues.put("person_id",person.getPerson_id());
            contentValues.put("person_name",person.getPerson_name());
            contentValues.put("person_role",person.getPerson_role());
            contentValues.put("person_about",person.getPerson_about());
            contentValues.put("person_avatar",person.getPerson_avatar());

            Db.db.insert("Person",contentValues);

            Toast.makeText(getActivity(), getString(R.string.saved), Toast.LENGTH_SHORT).show();

            goToPersonInfo(person.getPerson_id());


        } catch (Exception ex)
        {
            Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    private int getNewPersonID()
    {
        try
        {
            Cursor cur = Db.db.GetData("Select person_id from Person order by person_id desc");
            if (cur.getCount() > 0)
            {
                cur.moveToNext();
                int max = cur.getInt(0);
                Boolean[] lstCheck =new Boolean[max+1];
                for (int i = 1; i <= max; i++)
                {
                    cur.moveToPosition(i);
                    lstCheck[i]=true;
                }
                for (int i = 1; i <= max; i++)
                {
                    if(lstCheck[i]==false)
                        return i;
                }

                //
                return max+1;
            } else
            {
                return 1;
            }
        } catch (Exception ex)
        {
            ex.printStackTrace();
            return 1;
        }
    }

    private void updatePerson(Person person)
    {
        try
        {
            ContentValues contentValues=new ContentValues();
            contentValues.put("person_name",person.getPerson_name());
            contentValues.put("person_role",person.getPerson_role());
            contentValues.put("person_about",person.getPerson_about());
            contentValues.put("person_avatar",person.getPerson_avatar());

            Db.db.update("Person",contentValues,"person_id=?",new String[]{person.getPerson_id()+""});

            Toast.makeText(getActivity(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
            getActivity().onBackPressed();
        } catch (Exception ex)
        {
            Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
            ex.printStackTrace();
        }
    }

    private byte[] getAvatar(Drawable drawable)
    {
        Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
        Bitmap resizedBmp = bmp.createScaledBitmap(bmp, 960, 540, true);
        ByteArrayOutputStream op = new ByteArrayOutputStream();
        resizedBmp.compress(Bitmap.CompressFormat.PNG, 100, op);
        return op.toByteArray();
    }

    private void goToPersonInfo(int person_id){
        Fragment fragment = new SizeListPerson();
        Bundle bundle = new Bundle();
        bundle.putInt("person_id", person.getPerson_id());
        fragment.setArguments(bundle);

        FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(((ViewGroup)(getView().getParent())).getId(),fragment);
//        Log.d("123", ((ViewGroup)(getView().getParent())).getId()+" id in fragment");

        transaction.commit();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE)
        {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK)
            {
                Uri resultUri = result.getUri();
                imgAvatar.setImageURI(resultUri);
                isUseDefaultAvatar = false;
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE)
            {
                Exception error = result.getError();
            }
        }
    }

    class GenderAdapter extends BaseAdapter
    {
        Context context;
        String[] lstGender;

        public GenderAdapter(Context context, String[] lstGender)
        {
            this.context = context;
            this.lstGender = lstGender;
        }

        @Override
        public int getCount()
        {
            return lstGender.length;
        }

        @Override
        public Object getItem(int position)
        {
            return null;
        }

        @Override
        public long getItemId(int position)
        {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.layout_size_list_gender, null);

            ImageView img = (ImageView) convertView.findViewById(R.id.img_size_list_gender);
            TextView txt = (TextView) convertView.findViewById(R.id.txt_size_list_gender);

            switch (position)
            {
                case 0:
                    img.setImageDrawable(getResources().getDrawable(R.drawable.man));
                    txt.setText(getString(R.string.male));
                    break;
                case 1:
                    img.setImageDrawable(getResources().getDrawable(R.drawable.woman));
                    txt.setText(getString(R.string.female));
                    break;
                case 2:
                    img.setImageDrawable(getResources().getDrawable(R.drawable.boy));
                    txt.setText(getString(R.string.boy));
                    break;
                case 3:
                    img.setImageDrawable(getResources().getDrawable(R.drawable.girl));
                    txt.setText(getString(R.string.girl));
                    break;
            }

            return convertView;
        }
    }
}
