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

public class InstitutionAdapter extends RecyclerView.Adapter<InstitutionAdapter.InstitutionHolder> {

    private Context context;
    private List<CommonData> institutionData;

    public InstitutionAdapter(Context context, List dataLists) {
        this.context = context;
        this.institutionData = dataLists;
    }


    @NonNull
    @Override
    public InstitutionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.institution_list_item, viewGroup, false);

        InstitutionHolder institutionHolder = new InstitutionHolder(v);

        return institutionHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InstitutionHolder viewHolder, int i) {

        CommonData list = institutionData.get(i);

        viewHolder.imgMain.setImageResource(list.getImgMain());
        viewHolder.title.setText(list.getTitle());
        viewHolder.title2.setText(list.getTitle2());
        viewHolder.imgStar.setImageResource(list.getImgStar());
        viewHolder.valueStar.setText(list.getValueStar());


        // it will be price instead valueClock
        viewHolder.valueClock.setText(list.getValueClock());

        viewHolder.quantityPhoto.setText(list.getQuantityPhoto());
        viewHolder.imgPeople.setImageResource(list.getImgPeople());
        viewHolder.quantityPeople.setText(list.getQuantityPeople());
        viewHolder.visitors.setText(list.getVisitors());
    }

    @Override
    public int getItemCount() {
        return institutionData.size();

    }


    public class InstitutionHolder extends RecyclerView.ViewHolder{

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

        public InstitutionHolder(@NonNull View itemView) {
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

