package ua.com.up_site.guiderenttest;

import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class TopGuidesActivity extends Activity {

    ArrayList<GuideInfo> guideInfoList;

    GridLayoutManager mLayoutManager;

    final int NUMBER_OF_COLUMNS = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_guides);
        RecyclerView rvRecyclerView = findViewById(R.id.guides_recycler_view);
        //Создаём двадцать гидов
        guideInfoList = GuideInfo.createGuideList(20);
        //Из них создаём адаптер...
        final GuidesAdapter adapter = new GuidesAdapter(guideInfoList);
        //...и прикрепляем его
        rvRecyclerView.setAdapter(adapter);

        mLayoutManager = new GridLayoutManager(this, 3);

       mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
           @Override
           public int getSpanSize(int position) {
               switch(position % 2) {
                   case 0:
                       return 1;
                   default:
                       return 2;
               }
           }
       });

        rvRecyclerView.setLayoutManager(mLayoutManager);
    }

}
