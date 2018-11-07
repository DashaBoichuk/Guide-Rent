package ua.com.up_site.guiderenttest.routes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.models.CommonData;


public class RouteAdapter extends RecyclerView.Adapter<RouteAdapter.RouteHolder> {

    private Context context;
    private List<CommonData> routeData;

    public RouteAdapter(Context context, List dataLists) {
        this.context = context;
        this.routeData = dataLists;
    }


    @NonNull
    @Override
    public RouteHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
      View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.route_list_item, viewGroup, false);

       RouteHolder routeHolder = new RouteHolder(v);

        return routeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RouteHolder routeHolder, int i) {

        CommonData list = routeData.get(i);

        routeHolder.imgMain.setImageResource(list.getImgMain());
        routeHolder.title.setText(list.getTitle());
        routeHolder.title2.setText(list.getTitle2());
        routeHolder.imgStar.setImageResource(list.getImgStar());
        routeHolder.valueStar.setText(list.getValueStar());
        routeHolder.imgClock.setImageResource(list.getImgClock());
        routeHolder.valueClock.setText(list.getValueClock());
        routeHolder.quantityPhoto.setText(list.getQuantityPhoto());
        routeHolder.imgPeople.setImageResource(list.getImgPeople());
        routeHolder.quantityPeople.setText(list.getQuantityPeople());
        routeHolder.visitors.setText(list.getVisitors());

    }

    @Override
    public int getItemCount() {
        return routeData.size();
    }



    public class RouteHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgMain)
        ImageView imgMain;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.title2)
        TextView title2;
        @BindView(R.id.imgStar)
        ImageView imgStar;
        @BindView(R.id.valueStar)
        TextView valueStar;
        @BindView(R.id.imgClock)
        ImageView imgClock;
        @BindView(R.id.valueClock)
        TextView valueClock;
        @BindView(R.id.quantityPhoto)
        TextView quantityPhoto;
        @BindView(R.id.imgPeople)
        ImageView imgPeople;
        @BindView(R.id.quantityPeople)
        TextView quantityPeople;
        @BindView(R.id.visitors)
        TextView visitors;

        public RouteHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Toast.makeText(view.getContext(), "Successfully clicked ", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }


}
