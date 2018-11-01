package ua.com.up_site.guiderenttest.top_guides;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ua.com.up_site.guiderenttest.R;
import ua.com.up_site.guiderenttest.user_model.GuideInfo;

//Адаптер для RecyclerView в топе гидов.

public class GuidesAdapter extends RecyclerView.Adapter<GuidesAdapter.GuideViewHolder> {

    private List<GuideInfo> mGuideInfo;

    public interface OnItemClickListener {
        void onItemClick(GuideInfo item);
    }

    private final OnItemClickListener listener;

    public GuidesAdapter(List<GuideInfo> guideInfoList, OnItemClickListener _listener) {
        this.mGuideInfo = guideInfoList;
        this.listener = _listener;
    }

    @NonNull
    @Override
    public GuideViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.guides_custom_layout, viewGroup, false);

        return new GuideViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull GuidesAdapter.GuideViewHolder viewHolder, int position) {
        viewHolder.bind(mGuideInfo.get(position), listener);
        GuideInfo guideInfo = mGuideInfo.get(position);

        viewHolder.nameTextView.setText(guideInfo.getName());
        viewHolder.ageTextView.setText(guideInfo.getAge());


    }

    @Override
    public int getItemCount() {
        return mGuideInfo.size();
    }



    class GuideViewHolder extends RecyclerView.ViewHolder {

        TextView nameTextView;
        TextView ageTextView;
        ImageView guideProfileImage;

        GuideViewHolder(@NonNull View itemView) {
            super(itemView);

            ageTextView = itemView.findViewById(R.id.tvAge);
            nameTextView = itemView.findViewById(R.id.tvName);
            guideProfileImage = itemView.findViewById(R.id.guideProfileImage);
        }

        public void bind(final GuideInfo _item, final OnItemClickListener _listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    _listener.onItemClick(_item);
                }
            });
        }
    }


}
