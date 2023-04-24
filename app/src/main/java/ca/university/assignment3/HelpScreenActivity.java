package ca.university.assignment3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

public class HelpScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_screen);

        setAppDetails();
        setAuthorDetails();
        setResourcesDetails();
    }

    private void setAppDetails() {
        TextView appDetails = findViewById(R.id.txtViewAppDetails2);
        String message = "To play Gold finder click on the tiles of the game to find Gold. " +
                "With every click you may find gold or not find gold. if you do not find gold then the " +
                "tile will show you the number of tiles with gold in it's row and column " +
                "and then increase the scan count. If you click an already discovered gold, " +
                "It will show you the number of tiles with gold in that row and column.";

        appDetails.setText(message);
    }

    private void setAuthorDetails() {
        TextView authorDetails = findViewById(R.id.txtViewAuthor2);

        String link = "Gold finder is written by Mercygold Msaki " +
                "A third year computer Engineering student at Simon Fraser University" +
                ". Taking Introduction to Software Engineering (CMPT 276) taught by Dr.Brian Fraser. " +
                "Here is a link to the course website: https://opencoursehub.cs.sfu.ca/bfraser/grav-cms/cmpt276/home";

        String uri = "https://opencoursehub.cs.sfu.ca/bfraser/grav-cms/cmpt276/home";
        StringToLink(link,authorDetails,uri,link.length()-61,link.length());
    }

    private void setResourcesDetails() {
        TextView resourceDetails = findViewById(R.id.txtViewResources2);
        String link1 = "https://www.dreamstime.com/stock-illustration-cartoon-pirate-treasure-chest-full-gold-jewels-vector-object-image66499409";
        String link2 = "https://imageenvision.com/clipart/29405-royalty-free-cartoon-clip-art-of-a-stack-of-gold-coins-near-a-pot-of-leprechauns-gold-by-andy-nortnik";
        String link3 = "https://www.dreamstime.com/stock-photography-businessman-gold-bar-cartoon-concept-illustration-happy-man-big-image31731062";
        String link4 = "https://www.freepik.com/premium-vector/falling-coins-falling-money-flying-gold-coins-golden-rain_14052132.htm";

        String message = "Images by dreamStime: (" + link1 + ") and (" + link3 +") Image by envision: (" +
                link2 + ") Images by freepik: (" + link4 + ")";

        StringToLinkResource(message,resourceDetails);
    }

    private void StringToLink(String link,TextView resourceDetails,String uri, int startIndex, int endIndex){
        SpannableString links = new SpannableString(link);

        links.setSpan(linkSetUp(uri),startIndex,endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        resourceDetails.setText(links);
        resourceDetails.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void StringToLinkResource(String link,TextView resourceDetails){
        String link1 = "https://www.dreamstime.com/stock-illustration-cartoon-pirate-treasure-chest-full-gold-jewels-vector-object-image66499409";
        String link2 = "https://imageenvision.com/clipart/29405-royalty-free-cartoon-clip-art-of-a-stack-of-gold-coins-near-a-pot-of-leprechauns-gold-by-andy-nortnik";
        String link3 = "https://www.dreamstime.com/stock-photography-businessman-gold-bar-cartoon-concept-illustration-happy-man-big-image31731062";
        String link4 = "https://www.freepik.com/premium-vector/falling-coins-falling-money-flying-gold-coins-golden-rain_14052132.htm";

        SpannableString links = new SpannableString(link);

        links.setSpan(linkSetUp(link1),23,143, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        links.setSpan(linkSetUp(link2),150,272, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        links.setSpan(linkSetUp(link3),294,435, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        links.setSpan(linkSetUp(link4),457,link.length()-1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        resourceDetails.setText(links);
        resourceDetails.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private ClickableSpan linkSetUp(String link){
        return  new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Uri courseWebsite = Uri.parse(link);
                Intent intent = new Intent(Intent.ACTION_VIEW,courseWebsite);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }

            @Override
            public void updateDrawState(@NonNull TextPaint wordColour){
                wordColour.setColor(Color.BLUE);
                wordColour.setUnderlineText(true);
            }
        };
    }

    public static Intent makeIntent(Context context) {
        return new Intent(context,HelpScreenActivity.class);
    }
}