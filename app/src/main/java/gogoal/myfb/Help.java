package gogoal.myfb;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import gogoal.R;
import gogoal.myfb.Object.Constant;

/**
 * Created by Go Goal on 6/6/2017.
 */

public class Help extends AppCompatActivity {


    TextView tv,tv2;
    Typeface font;
    Button play,diret;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.helper);

        getSupportActionBar().setTitle("HELP");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_icn_close);

        font=Typeface.createFromAsset(getAssets(),"zawgyi.ttf");

        tv= (TextView) findViewById(R.id.tv1);
        tv2= (TextView) findViewById(R.id.tv2);

        play= (Button) findViewById(R.id.playbtn);
        diret= (Button) findViewById(R.id.directbtn);


        tv.setTypeface(font);
        tv2.setTypeface(font);


        tv.setText(Html.fromHtml("<b>အခ်ိန္ဘယ္လို သက္တမ္း တိုးရမလည္း ?</b><br>" +
                "<br>" +
                "ပထမ ဦးစြာ အေနျဖင့္ F time  Application ကို Google Play Store ေပၚမွ တစ္ဆင့္ Download ရယူ၍ Install လုပ္ပါ ။<br>" +
                "<br>" +
                "Playstore Application မရွိပါက Direct Link ကေန Down ၍ Install လုပ္ပါ ။<br>" +
                "<br>" +
                "App ကို Install လုပ္ျပီးေနာက္ပါ ေအာက္ပါ ပံု အတိုင္း App တစ္ခုကို ရရွိပါလိမ့္မည္ ။"));

        tv2.setText("သင့္တြင္ Ticket ရွိပါက Spin ခလုတ္ကို ႏိုပ္၍ F Movie အတြက္ သင္ရရွိမယ့္ အခ်ိန္ကို ကံစမ္း ကစား ႏိုင္ပါသည္။\n" +
                "\n" +
                "အကယ္၍ သင့္တြင္ Ticket မရွိပါက Get Ticket ခလုတ္ကို ႏိုပ္ပါ ။ \n" +
                "\n" +
                "ႏိုပ္လိုက္ပါက Video ေၾကာ္ျငာတစ္ခုတတ္လာပါလိမ့္မည္ ။ Video ဆံုးတဲ့ အထိၾကည့္ေပးဖို႔ေတာ့ လိုအပ္ပါသည္ ။\n" +
                "\n" +
                "Video ဆံုးသြားသည့္ေနာက္တြင္ Ticket တစ္ခုရရွိမည္ ျဖစ္ပါသည္ ။ ထို႔ေနာက္ Spin ခလုတ္ကို ႏိုပ္၍ F Movie အတြက္ သင္ရရွိမယ့္ အခ်ိန္ကို ကံစမ္း ကစား ႏိုင္ပါသည္။\n\n\n");


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                        .parse(Constant.playurl));
                startActivity(intent);
            }
        });


        diret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri
                        .parse(Constant.directurl));
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }
}
