package ua.com.up_site.guiderenttest.notifications;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(NotificationInfo item);
    }

    private List<NotificationInfo> notificationInfos;

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    NotificationAdapter(List<NotificationInfo> notificationInfos, NotificationAdapter.OnItemClickListener _listener) {

    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder notificationViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

}
