package sunil.worldpeace;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by sunil on 9/8/16.
 */
public class BulletinHolder extends RecyclerView.ViewHolder {

    CardView bulletinCard;
    TextView bulletinText;
    LinearLayout disasterIconsRow;
    LinearLayout refugeeIconsRow;

    public BulletinHolder(View itemView) {
        super(itemView);
        bulletinCard = (CardView) itemView.findViewById(R.id.bulletin_card);
        bulletinText = (TextView) itemView.findViewById(R.id.bulletin_title);
        disasterIconsRow = (LinearLayout) itemView.findViewById(R.id.bulletin_disaster_row);
        refugeeIconsRow = (LinearLayout) itemView.findViewById(R.id.bulletin_people_row);
    }



}
