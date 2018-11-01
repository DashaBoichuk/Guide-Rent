package ua.com.up_site.guiderenttest.adapters;

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

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationHolder> {

    private Context context;
    private List<CommonData> locationData;

    public LocationAdapter(Context context, List locationData) {
        this.context = context;
        this.locationData = locationData;
    }

    @NonNull
    @Override
    public LocationHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.location_list_item, viewGroup, false);


        LocationHolder locationHolder = new LocationHolder(v);

        return locationHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull LocationHolder locationHolder, int i) {
        CommonData list = locationData.get(i);

        locationHolder.imgMain.setImageResource(list.getImgMain());
        locationHolder.title.setText(list.getTitle());
        locationHolder.imgStar.setImageResource(list.getImgStar());
        locationHolder.valueStar.setText(list.getValueStar());
        locationHolder.imgClock.setImageResource(list.getImgClock());
        locationHolder.valueClock.setText(list.getValueClock());
        locationHolder.quantityPhoto.setText(list.getQuantityPhoto());
        locationHolder.imgPeople.setImageResource(list.getImgPeople());
        locationHolder.quantityPeople.setText(list.getQuantityPeople());
        locationHolder.visitors.setText(list.getVisitors());
    }

    @Override
    public int getItemCount() {
        return locationData.size();
    }

    public class LocationHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.imgMain)
        ImageView imgMain;
        @BindView(R.id.title)
        TextView title;
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

        public LocationHolder(@NonNull View itemView) {
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

