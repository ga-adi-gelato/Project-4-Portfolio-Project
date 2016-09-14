package sunil.worldpeace;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class WebViewActivity extends AppCompatActivity {

    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        webview = (WebView) findViewById(R.id.webview);
        Intent intent = getIntent();
        String bulletinurl = intent.getStringExtra("URL");
        webview.loadUrl(bulletinurl);


    }
}
