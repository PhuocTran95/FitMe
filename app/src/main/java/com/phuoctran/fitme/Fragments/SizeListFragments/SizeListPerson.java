package com.phuoctran.fitme.Fragments.SizeListFragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.phuoctran.fitme.Databases.Db;
import com.phuoctran.fitme.Models.Person;
import com.phuoctran.fitme.Models.SizeDetail;
import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Util.RoleDetailUtil;
import com.phuoctran.fitme.Util.SizeDetailUtil;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.phuoctran.fitme.Databases.Db.db;

/**
 * Created by VN-PC on 2017/05/27.
 */

public class SizeListPerson extends Fragment
{
    Person person;
    CircleImageView avatar;
    ImageView backAvatar;
    TextView name;
    TextView sub;
    ListView lstSizeDetail;
    ImageButton btnEdit;
    ImageButton btnBack;
    ImageButton btnAbout;
    SizeDetailAdapter adapter;
    List<SizeDetail> lstSize;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_size_list_person_info, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        avatar = (CircleImageView) getActivity().findViewById(R.id.img_person_avatar);
        backAvatar = (ImageView) getActivity().findViewById(R.id.img_person_back_avatar);
        name = (TextView) getActivity().findViewById(R.id.person_name);
        btnAbout = (ImageButton) getActivity().findViewById(R.id.btn_person_note);
        sub = (TextView) getActivity().findViewById(R.id.person_sub);
        lstSizeDetail = (ListView) getActivity().findViewById(R.id.person_list_size);
        btnEdit = (ImageButton) getActivity().findViewById(R.id.btn_person_edit);
        btnBack = (ImageButton) getActivity().findViewById(R.id.btn_person_back);
        setPersonInfo();
    }

    private void setPersonInfo()
    {
        person = new Person();
        int id = getArguments().getInt("person_id");
        Cursor cur = db.GetData("Select * from Person where person_id=" + id);
        cur.moveToNext();
        person.setPerson_id(cur.getInt(0));
        person.setPerson_name(cur.getString(1));
        person.setPerson_role(cur.getInt(2));
        person.setPerson_avatar(cur.getBlob(3));
        person.setPerson_about(cur.getString(4));
        cur.close();

        try
        {
            Bitmap bmp = BitmapFactory.decodeByteArray(person.getPerson_avatar(), 0, person.getPerson_avatar().length);
            avatar.setImageBitmap(bmp);
            backAvatar.setImageBitmap(bmp);
        } catch (Exception ex)
        {
            avatar.setImageResource(R.drawable.face);
            backAvatar.setImageResource(R.drawable.face);
        }
        name.setText(person.getPerson_name());
        sub.setText(RoleDetailUtil.getRoleName(getActivity(), person.getPerson_role()));

        btnEdit.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Bundle bundle = new Bundle();
                bundle.putInt("person_id",person.getPerson_id());

                Fragment fragment = new AddEditPerson();
                fragment.setArguments(bundle);

                FragmentTransaction transaction=getActivity().getSupportFragmentManager().beginTransaction();
                transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
                transaction.replace(R.id.container,fragment,"");
                transaction.addToBackStack("");
                transaction.commit();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getActivity().onBackPressed();
            }
        });

        btnAbout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.layout_size_list_note);

                TextView txtAbout = (TextView) dialog.findViewById(R.id.person_about);
                txtAbout.setText(person.getPerson_about());

                Button btnClose = (Button) dialog.findViewById(R.id.btn_person_about_close);
                btnClose.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.cancel();
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
            }
        });

        getSizeDetail();
        lstSizeDetail.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id)
            {
                final Dialog dialog = new Dialog(getActivity());
                dialog.requestWindowFeature(1);
                dialog.setContentView(R.layout.layout_size_edit);
                dialog.setCancelable(false);

                TextView txtHeader = (TextView) dialog.findViewById(R.id.txt_size_edit_header);
                final TextView txtSize = (TextView) dialog.findViewById(R.id.txt_size_edit_size);
                final TextView txtNote = (TextView) dialog.findViewById(R.id.txt_size_edit_note);
                Button btnSave = (Button) dialog.findViewById(R.id.btn_size_edit_save);
                Button btnCancel = (Button) dialog.findViewById(R.id.btn_size_edit_cancel);

                txtHeader.setText(getTypeNameById(lstSize.get(position).getDetail_type()));

                txtSize.setText(lstSize.get(position).getDetail_value() == null ? "" : lstSize.get(position).getDetail_value());

                txtNote.setText(lstSize.get(position).getDetail_note() == null ? "" : lstSize.get(position).getDetail_note());
                InputFilter[] filterArray = new InputFilter[1];
                filterArray[0] = new InputFilter.LengthFilter(200);
                txtNote.setFilters(filterArray);

                btnCancel.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        dialog.cancel();
                    }
                });
                btnSave.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v)
                    {
                        try
                        {
                            final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                            builder.setTitle(getString(R.string.saveAlert));
                            builder.setNegativeButton(getString(android.R.string.yes), new DialogInterface.OnClickListener()
                            {
                                @Override
                                public void onClick(DialogInterface dialog, int which)
                                {
                                    if (lstSize.get(position).getDetail_id() == -1)
                                    {
                                        Db.db.ChangeData(String.format("insert into SizeDetail(detail_id,detail_value,detail_note,detail_person,detail_type)\n" +
                                                        "values(%1$s,'%2$s','%3$s',%4$s,%5$s)", SizeDetailUtil.getNewRoleID(getActivity()),
                                                txtSize.getText().toString(),
                                                txtNote.getText().toString(),
                                                lstSize.get(position).getDetail_person(),
                                                lstSize.get(position).getDetail_type()));
                                    } else
                                    {
                                        String vl = txtSize.getText().toString();
                                        String note = txtNote.getText().toString();
                                        int id = lstSize.get(position).getDetail_id();
                                        Db.db.ChangeData(String.format("update SizeDetail\n" +
                                                "set detail_value='%1$s', detail_note='%2$s'\n" +
                                                "where detail_id=%3$s", vl, note, id));
                                    }
                                    Toast.makeText(getActivity(), getString(R.string.saved), Toast.LENGTH_SHORT).show();
//                                    dialog.cancel();
                                    getSizeDetail();
                                }
                            });
                            builder.setPositiveButton(getString(android.R.string.no), null);
                            builder.show();
                        } catch (Exception ex)
                        {
                            Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                            dialog.cancel();
                        }
                    }
                });

                dialog.show();
                dialog.getWindow().setLayout(DrawerLayout.LayoutParams.MATCH_PARENT, DrawerLayout.LayoutParams.WRAP_CONTENT);
            }
        });
    }

    private void getSizeDetail()
    {
        lstSize = new ArrayList<>();

        for (int i = 1; i <= 5; i++)
        {
            Cursor cursor = Db.db.GetData(String.format("Select * from SizeDetail where detail_person = %1$s and detail_type=%2$s", person.getPerson_id(), i));
            if (cursor.getCount() > 0)
            {
                cursor.moveToNext();
                lstSize.add(new SizeDetail(cursor.getInt(0),
                        cursor.getString(1) == null ? "" : cursor.getString(1),
                        cursor.getString(2) == null ? "" : cursor.getString(2),
                        person.getPerson_id(),
                        i));
            } else
            {
                lstSize.add(new SizeDetail(-1,
                        "",
                        "",
                        person.getPerson_id(),
                        i));
            }
            cursor.close();
        }

        adapter = new SizeDetailAdapter(getActivity(), lstSize);
        lstSizeDetail.setAdapter(adapter);
    }

    private String getTypeNameById(int detail_type)
    {
        switch (detail_type)
        {
            case 1:
                return getActivity().getString(R.string.nav_tops);
            case 2:
                return getActivity().getString(R.string.nav_bottoms);
            case 3:
                return getActivity().getString(R.string.nav_shoe);
            case 4:
                return getActivity().getString(R.string.nav_hat);
            case 5:
                return getActivity().getString(R.string.nav_ring);
        }
        return getActivity().getString(R.string.app_name);
    }

    private int getImageTypeById(int detail_type)
    {
        switch (detail_type)
        {
            case 1:
                return R.drawable.shirt2;
            case 2:
                return R.drawable.pants2;
            case 3:
                return R.drawable.shoe2;
            case 4:
                return R.drawable.hat2;
            case 5:
                return R.drawable.ring2;
        }
        return R.drawable.logo;
    }

    class SizeDetailAdapter extends BaseAdapter
    {
        Context context;
        List<SizeDetail> sizeDetailList;

        public SizeDetailAdapter(Context context, List<SizeDetail> sizeDetailList)
        {
            this.context = context;
            this.sizeDetailList = sizeDetailList;
        }

        @Override
        public int getCount()
        {
            return sizeDetailList.size();
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
            convertView = inflater.inflate(R.layout.layout_size_list_size_detail_list, null);

            ImageView img = (ImageView) convertView.findViewById(R.id.img_size_detail_list);
            TextView txtType = (TextView) convertView.findViewById(R.id.txt_size_detail_list_type);
            TextView txtAbout = (TextView) convertView.findViewById(R.id.txt_size_detail_list_about);
            TextView txtSize = (TextView) convertView.findViewById(R.id.txt_size_detail_list_size);

            img.setImageResource(getImageTypeById(sizeDetailList.get(position).getDetail_type()));
            txtType.setText(getTypeNameById(sizeDetailList.get(position).getDetail_type()));
            txtSize.setText(sizeDetailList.get(position).getDetail_value().trim().length() == 0 ? "N/A" : sizeDetailList.get(position).getDetail_value());
            if (sizeDetailList.get(position).getDetail_note().trim().length() == 0)
            {
                txtAbout.setVisibility(View.GONE);
                txtType.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));
                txtType.setGravity(Gravity.CENTER_VERTICAL);
            } else
            {
                txtAbout.setVisibility(View.VISIBLE);
                txtAbout.setText(sizeDetailList.get(position).getDetail_note());
                txtType.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                txtType.setGravity(Gravity.NO_GRAVITY);
            }

            return convertView;
        }
    }
}
