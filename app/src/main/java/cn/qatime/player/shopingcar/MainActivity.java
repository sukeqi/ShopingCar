package cn.qatime.player.shopingcar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private List<Item> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        list = (ListView) findViewById(R.id.listview);
        datas.add(new Item("银行卡"));
        datas.add(new Item("支付宝"));
        datas.add(new Item("微信"));
        datas.add(new Item("充值卡"));
        datas.get(0).isSelect = true;//默认选中第一个
        final BaseAdapter adapter = new CommonAdapter<Item>(this, datas, R.layout.item) {
            @Override
            public void convert(ViewHolder holder, Item item, int position) {
                holder.setText(R.id.text, item.name);
                if (item.isSelect) {
                    ((TextView) holder.getView(R.id.text)).setTextColor(0xffff0000);
                    ((TextView) holder.getView(R.id.text)).setBackgroundColor(0xff000000);
                } else {
                    ((TextView) holder.getView(R.id.text)).setTextColor(0xff0ff000);
                    ((TextView) holder.getView(R.id.text)).setBackgroundColor(0xffffffff);
                }
            }
        };
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                for (int i = 0; i < datas.size(); i++) {
                    if (i == position) {
                        datas.get(i).isSelect = true;
                    } else {
                        datas.get(i).isSelect = false;
                    }
                }
                adapter.notifyDataSetChanged();
                //在这根据position切换fragment

            }
        });
    }
}
