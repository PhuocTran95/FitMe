package com.phuoctran.fitme.Fragments.SizeListFragments;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ListView;
import android.widget.SectionIndexer;
import android.widget.TextView;
import android.widget.Toast;

import com.phuoctran.fitme.Databases.Db;
import com.phuoctran.fitme.Models.Person;
import com.phuoctran.fitme.R;
import com.phuoctran.fitme.Util.RoleDetailUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.phuoctran.fitme.Databases.Db.db;

/**
 * Created by VN-PC on 2017/05/16.
 */

public class SizeListMain extends Fragment
{
    FloatingActionButton floatingActionButton;
    ListView listView;
    PersonAdapter adapter;
    ArrayList<Person> lstPerson;
    ArrayList<Person> lstPersonFull;
    String filterKeyword = "";

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_size_list_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        floatingActionButton = (FloatingActionButton) getActivity().findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                changeFragmentFromFragment(new AddEditPerson());
            }
        });

        listView = (ListView) getActivity().findViewById(R.id.size_list_listPerson);

        lstPerson = new ArrayList<>();
        lstPersonFull = new ArrayList<>();

        Cursor cursor = Db.db.GetData("Select * from Person");
        while (cursor.moveToNext())
        {
            Person person = new Person(cursor.getInt(0), cursor.getString(1), cursor.getInt(2), cursor.getBlob(3), cursor.getString(4));
            lstPerson.add(person);
        }
        cursor.close();

        Collections.sort(lstPerson, new Comparator<Person>()
        {
            @Override
            public int compare(Person o1, Person o2)
            {
                return o1.getPerson_name().compareTo(o2.getPerson_name());
            }
        });

        lstPersonFull.addAll(lstPerson);
        adapter = new PersonAdapter(getActivity(), lstPerson);
        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id)
            {
                final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                String[] lstSelect = {getString(R.string.edit), getString(R.string.delete)};
                builder.setItems(lstSelect, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if (which == 0)
                        {
                            Bundle bundle = new Bundle();
                            bundle.putInt("person_id", lstPerson.get(position).getPerson_id());

                            Fragment fragment = new AddEditPerson();
                            fragment.setArguments(bundle);
                            changeFragmentFromFragment(fragment);
                        } else
                        {
                            try
                            {
                                final AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());

                                final String name = lstPerson.get(position).getPerson_name();

                                alert.setTitle(String.format(getString(R.string.deleteAlert), name));
                                alert.setNegativeButton(getString(android.R.string.yes), new DialogInterface.OnClickListener()
                                {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which)
                                    {
                                        db.ChangeData("Delete from Person where person_id=" + lstPerson.get(position).getPerson_id());
                                        lstPersonFull.remove(lstPerson.get(position));
                                        lstPerson.remove(position);
                                        adapter.UpdateSourceAfterChanged(lstPersonFull);
                                        adapter.notifyDataSetChanged();
                                        Toast.makeText(getActivity(), String.format(getString(R.string.deleteConfirm), name), Toast.LENGTH_SHORT).show();
                                        dialog.cancel();
                                    }
                                });
                                alert.setPositiveButton(getString(android.R.string.no), null);
                                alert.show();
                            } catch (Exception ex)
                            {
                                Toast.makeText(getActivity(), getString(R.string.error), Toast.LENGTH_SHORT).show();
                                dialog.cancel();
                            }
                        }
                    }
                });
                builder.show();
                return true;
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Fragment fragment = new SizeListPerson();
                Bundle bundle = new Bundle();
                bundle.putInt("person_id", lstPerson.get(position).getPerson_id());
                fragment.setArguments(bundle);
                changeFragmentFromFragment(fragment);
            }
        });

        //Checking alter option menu of Main Acivity
        setHasOptionsMenu(true);
    }

    private void changeFragmentFromFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);
        transaction.replace(((ViewGroup)(getView().getParent())).getId(), fragment);
//        Log.d("123", ((ViewGroup)(getView().getParent())).getId()+" id in fragment");
        transaction.addToBackStack("");
        transaction.commit();
    }

    //Alter option menu of Main Acivity
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        getActivity().getMenuInflater().inflate(R.menu.search_bar, menu);
        MenuItem item = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) item.getActionView();
        EditText searchEditText = (EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text);
        searchEditText.setTextColor(getResources().getColor(R.color.white));
        searchEditText.setHintTextColor(getResources().getColor(R.color.white));
        searchEditText.setHint(R.string.text_hint);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener()
        {
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText)
            {
                filterKeyword = newText;
                adapter.getFilter().filter(newText);
                return true;
            }
        });
    }

    class PersonAdapter extends BaseAdapter implements SectionIndexer, Filterable
    {
        private Context context;
        private ArrayList<Person> lstPerson;
        private ArrayList<Person> lstPersonFull;
        private HashMap<String, Integer> alphaIndexer;
        String[] sections;

        public PersonAdapter(Context context, ArrayList<Person> lstPerson)
        {
            this.context = context;
            this.lstPerson = lstPerson;
            this.lstPersonFull = new ArrayList<>();
            this.lstPersonFull.addAll(lstPerson);
            IndexSection();
        }

        public void UpdateSourceAfterChanged(ArrayList<Person> newSource)
        {
            this.lstPersonFull = new ArrayList<>();
            this.lstPersonFull.addAll(newSource);
        }

        private void IndexSection()
        {
            alphaIndexer = new HashMap<String, Integer>();

            for (int i = 0; i < getCount(); i++)
            {
                Person element = lstPerson.get(i);
                String sb = element.getPerson_name().substring(0, 1).toUpperCase();
                if (!alphaIndexer.containsKey(sb))
                    alphaIndexer.put(sb, i);
            }

            Set<String> keys = alphaIndexer.keySet();
            ArrayList<String> keyList = new ArrayList<String>(keys); // list can be sorted

            Collections.sort(keyList);

            sections = new String[keyList.size()]; // simple conversion to an
            // array of object
            keyList.toArray(sections);
        }

        @Override
        public int getCount()
        {
            return lstPerson.size();
        }

        @Override
        public Object getItem(int position)
        {
            return lstPerson.get(position);
        }

        @Override
        public long getItemId(int position)
        {
            return lstPerson.get(position).getPerson_id();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            convertView = ((LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.layout_size_list_person, null);

            CircleImageView avatar = (CircleImageView) convertView.findViewById(R.id.size_list_avatar);
            TextView name = (TextView) convertView.findViewById(R.id.size_list_Name);
            TextView role = (TextView) convertView.findViewById(R.id.size_list_Role);

            byte[] ava = lstPerson.get(position).getPerson_avatar();
            if (ava != null)
            {
                Bitmap bmp = BitmapFactory.decodeByteArray(ava, 0, ava.length);
                avatar.setImageBitmap(bmp);
            } else
            {
                avatar.setImageResource(R.drawable.face);
            }

            name.setText(lstPerson.get(position).getPerson_name());
            role.setText(RoleDetailUtil.getRoleName(getActivity(), lstPerson.get(position).getPerson_role()));

            return convertView;
        }

        @Override
        public Object[] getSections()
        {
            return sections;
        }

        @Override
        public int getPositionForSection(int sectionIndex)
        {
            return alphaIndexer.get(sections[sectionIndex]);
        }

        @Override
        public int getSectionForPosition(int position)
        {
            for (int i = sections.length - 1; i >= 0; i--)
            {
                if (position > alphaIndexer.get(sections[i]))
                    return i;
            }
            return 0;
        }

        @Override
        public Filter getFilter()
        {
            Filter filter = new Filter()
            {
                @Override
                protected FilterResults performFiltering(CharSequence constraint)
                {
                    String filterString = constraint.toString().toLowerCase();

                    FilterResults results = new FilterResults();

                    final List<Person> list = new ArrayList<>();
                    list.addAll(lstPersonFull);

                    int count = list.size();
                    final ArrayList<Person> nlist = new ArrayList<Person>(count);

                    String filterableString;

                    for (int i = 0; i < count; i++)
                    {
                        filterableString = list.get(i).getPerson_name();
                        if (filterableString.toLowerCase().contains(filterString))
                        {
                            nlist.add(list.get(i));
                        }
                    }

                    results.values = nlist;
                    results.count = nlist.size();

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results)
                {
                    lstPerson.clear();
                    lstPerson.addAll((ArrayList<Person>) results.values);
                    notifyDataSetChanged();
                }
            };

            return filter;
        }

        @Override
        public void notifyDataSetChanged()
        {
            super.notifyDataSetChanged();
            this.IndexSection();
        }
    }
}
