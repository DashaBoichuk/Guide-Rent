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

        // (least common multiple of 1 and 2)
        mLayoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);

       mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
           @Override
           public int getSpanSize(int position) {
               // 3 is the sum of items in one repeated section
               switch (position % 3) {
                   // first two items span 2 columns each
                   case 0:
                   case 1:
                       return 1;
                   // next one item span in 1 columns

                   case 2:
                       return 2;
               }
               throw new IllegalStateException("internal error");
           }
       });

        rvRecyclerView.setLayoutManager(mLayoutManager);
    }

}
