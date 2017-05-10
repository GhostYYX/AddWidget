package com.yyx.addwidget;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private LinearLayout add_linear;
    private int index=1;

    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        add_linear = (LinearLayout) findViewById(R.id.add_linear);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this,"加控件",Toast.LENGTH_LONG).show();
                addButton();
            }
        });
    }

    private void addButton(){
        LayoutInflater inflater = getLayoutInflater();
        final View viewUrl = inflater.inflate(R.layout.activity_main_button, null);
        Button button = (Button) viewUrl.findViewById(R.id.button);
        button.setText("列队"+index);

        LinearLayout.LayoutParams UrL = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        viewUrl.setLayoutParams(UrL);
        add_linear.addView(viewUrl);
        index++;
        //获取焦点
        //让最后一个一直显示
        add_linear.setFocusable(true);
        add_linear.setFocusableInTouchMode(true);
        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Toast.makeText(MainActivity.this,"弹窗",Toast.LENGTH_LONG).show();
            //弹出框,实现页面嵌套
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.activity_main_button, null);
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final AlertDialog dialog = builder.create();
            dialog.setView(layout);
            Window window = dialog.getWindow();

            window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
