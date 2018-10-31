package ua.com.up_site.guiderenttest.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.adapters.InstitutionAdapter;
import ua.com.up_site.guiderenttest.models.CommonData;

public class InstitutionFragment extends Fragment {
    private Unbinder unbinder;
    @BindView(R.id.recycler_institution)
    RecyclerView recyclerInstitution;
    @BindView(R.id.searchViewInstitution)
    SearchView searchViewInstitution;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;

    List<CommonData> institutionData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_institution, container, false);
        unbinder = ButterKnife.bind(this, view);

        searchViewInstitution.setBackgroundResource(R.drawable.frame);

        recyclerInstitution.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        recyclerInstitution.setLayoutManager(layoutManager);

        initData();
        return view;


    }

    private void initData() {
        institutionData = new ArrayList<>();

        institutionData.add(new CommonData(R.drawable.alley,"Ресторан", "Интольже", R.drawable.star, "6.3",  "20 $", "Средний чек", R.drawable.people, "188", "Посещения"));
        institutionData.add(new CommonData(R.drawable.april,"Ресторан", "Интольже", R.drawable.star, "7.9",  "20 $", "Средний чек", R.drawable.people, "854", "Посещения"));
        institutionData.add(new CommonData(R.drawable.philarmonia,"Ресторан", "Интольже", R.drawable.star, "6.0", "20 $", "Средний чек", R.drawable.people, "816", "Посещения"));
        institutionData.add(new CommonData(R.drawable.morvokzal,"Ресторан", "Интольже", R.drawable.star, "5.4",  "20 $", "Средний чек", R.drawable.people, "993", "Посещения"));
        institutionData.add(new CommonData(R.drawable.kinostudia,"Ресторан", "Интольже", R.drawable.star, "1.6", "20 $", "Средний чек", R.drawable.people, "833", "Посещения"));
        institutionData.add(new CommonData(R.drawable.opera_theater,"Ресторан", "Интольже", R.drawable.star, "3.2",  "20 $", "Средний чек", R.drawable.people, "136", "Посещения"));
        institutionData.add(new CommonData(R.drawable.memorial,"Ресторан", "Интольже", R.drawable.star, "9.6",  "20 $", "Средний чек", R.drawable.people, "441", "Посещения"));

        mAdapter = new InstitutionAdapter(getContext(), institutionData);
        recyclerInstitution.setAdapter(mAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}