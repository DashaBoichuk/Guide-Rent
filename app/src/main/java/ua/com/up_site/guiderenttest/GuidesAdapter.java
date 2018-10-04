package ua.com.up_site.guiderenttest;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class GuidesAdapter extends RecyclerView.Adapter<GuidesAdapter.GuideViewHolder>  {

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
        GuideInfo guideInfo = mGuideInfo.get(position);

        TextView nameTextView = viewHolder.nameTextView;
        TextView ageTextView = viewHolder.ageTextView;
        ImageView profilePic = viewHolder.guideProfileImage;

        nameTextView.setText(guideInfo.getName());
        ageTextView.setText(guideInfo.getAge());


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
    }

    private List<GuideInfo> mGuideInfo;

    public GuidesAdapter(List<GuideInfo> guideInfoList) {
        this.mGuideInfo = guideInfoList;
    }
}
