package sunil.worldpeace;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.List;
import sunil.worldpeace.CardObjects.Bulletin;
import sunil.worldpeace.CardObjects.CountryCard;
import sunil.worldpeace.CardObjects.CountryOverview;

/**
 * Created by sunil on 9/9/16.
 */
public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {


    Context mContext;
    public List<CountryCard> mList;
    RecyclerView.ViewHolder mViewHolder;
    Bulletin bulletinCard;

    private final int OVERVIEW = 0;
    private final int BULLETIN = 1;
    private final int REFUGEE_COUNTRIES = 2;
    private final int REFUGEE_CHART = 3;
    private final int ORGANIZATIONS = 4;

    RecyclerView.ItemAnimator animator;

    public CardAdapter(List<CountryCard> card){
        this.mList = card;
    }

    //TODO: finish other cards to finish this
    @Override
    public int getItemViewType(int position) {
        //return super.getItemViewType(position);
        if (mList.get(position)instanceof CountryOverview)
            return OVERVIEW;
        else if(mList.get(position)instanceof Bulletin)
            return BULLETIN;
        return -1;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        switch(viewType){
            //TODO: COMPLETE THIS
            case OVERVIEW:
                break;
            case BULLETIN:
                View bulletinView = LayoutInflater.from(parent.getContext()).inflate(R.layout.bulletin_card,parent,false);
                mViewHolder = new BulletinHolder(bulletinView);
                break;
        }
        return mViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(mList.get(position)instanceof Bulletin){
            bulletinCard = (Bulletin) mList.get(position);
        }

        final Bulletin bulletin = (Bulletin) mList.get(position);

        switch(mViewHolder.getItemViewType()){
            case BULLETIN:
                final BulletinHolder bulletinHolder = (BulletinHolder) holder;
                bulletinHolder.bulletinText.setText(bulletinCard.getmTitle());
                bulletinHolder.bulletinCard.setOnClickListener(new View.OnClickListener() {




                    @Override
                    public void onClick(View v) {
                        // Toast.makeText(mContext,"Clicked on "+(position+1)+"\n"+ bulletin.getmBulletinURL(),Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(v.getContext(),WebViewActivity.class);
                        intent.putExtra("URL",bulletin.getmBulletinURL());
                        mContext.startActivity(intent);

                    }
                });


                if(bulletin.isAged()) {
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_aged);
                    bulletinHolder.refugeeIconsRow.addView(view);
                }
                else if(bulletin.isAlienInvasion()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_alieninvasion);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isChildren()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_children);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isColdWave()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_coldwave);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isDisabled()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_disabilities);
                    bulletinHolder.refugeeIconsRow.addView(view);
                }
                else if(bulletin.isDrought()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_drought);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isEarthquake()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_earthquake);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isExtraTropicalCyclone()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_extratropicalcyclone);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isFire()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_fire);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isFlashFlood()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_flashflood);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isFlood()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_flood);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isHeatWave()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_heatwave);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isIDP()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_idp);
                    bulletinHolder.refugeeIconsRow.addView(view);
                }
                else if(bulletin.isInsectInfestation()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_insectinfestation);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isLandSlide()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_landslide);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isOther()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_other);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isRefugee()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_refugee);
                    bulletinHolder.refugeeIconsRow.addView(view);
                }
                else if(bulletin.isSevereLocalStorm()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_severelocalstorm);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isSnowAvalanche()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_avalanche);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isStormSurge()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_stormsurge);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isTechnologicalDisaster()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_techdisaster);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isTropicalCyclone()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_tropicalcyclone);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isTsunami()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_tsunami);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isVolcano()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_volcano);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isWildFire()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_wildfire);
                    bulletinHolder.disasterIconsRow.addView(view);
                }
                else if(bulletin.isWomen()){
                    ImageView view = new ImageView(mContext);
                    view.setImageResource(R.drawable.vector_drawable_women);
                    bulletinHolder.refugeeIconsRow.addView(view);
                }
                break;
        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    @Override
    public void onClick(View v) {

    }

}
