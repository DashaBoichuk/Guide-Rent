package ua.com.up_site.guiderenttest;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TopGuidesActivity extends Activity {

    ArrayList<GuideInfo> guideInfoList;

    LinearLayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_guides);
        RecyclerView rvRecyclerView = findViewById(R.id.guides_recycler_view);
        //Создаём двадцать гидов
        guideInfoList = GuideInfo.createGuideList(20);
        //Из них создаём адаптер...
        GuidesAdapter adapter = new GuidesAdapter(guideInfoList);
        //...и прикрепляем его
        rvRecyclerView.setAdapter(adapter);

        rvRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

}
