package ua.com.up_site.guiderenttest.TopGuidesPackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import ua.com.up_site.guiderenttest.R;

//Адаптер для интересов и сервисов в профиле гида
public class GuideProfileTagAdapter extends RecyclerView.Adapter<GuideProfileTagAdapter.TagAdapterViewHolder> {

    //tagArray - передаётся во время создания адаптера, и содержит в себе все тэги
    private ArrayList<String> tagArray;

    //Конструктор получает тэги в виде обычного строкового массива (сервисы или интересы)
    public GuideProfileTagAdapter(ArrayList<String> tagArray) {
        this.tagArray = tagArray;
    }

    @NonNull
    @Override
    public TagAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.round_tag_custom_layout, viewGroup, false);

        return new GuideProfileTagAdapter.TagAdapterViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull TagAdapterViewHolder tagAdapterViewHolder, int position) {
        String tag = tagArray.get(position);

        tagAdapterViewHolder.tagTextView.setText(tag);

    }

    @Override
    public int getItemCount() {
        return tagArray.size();
    }

    class TagAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView tagTextView;

        TagAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            tagTextView = itemView.findViewById(R.id.profile_tag);
        }
    }
}
