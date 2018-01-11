package com.gdcp.mipushdemo.mvpretrofitrxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gdcp.mipushdemo.mvpretrofitrxjava.service.entity.Book;
import com.gdcp.mipushdemo.mvpretrofitrxjava.service.presenter.BookPresenter;
import com.gdcp.mipushdemo.mvpretrofitrxjava.service.view.BookView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements BookView{
    private EditText edtKeyword;
    private Button button;
    private ListView lisview;



    private List<Book.BooksBean> books=new ArrayList<>();
    private MyAdapter myAdapter=new MyAdapter();

    private BookPresenter mBookPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBookPresenter= new BookPresenter(this);
        mBookPresenter.onCreate();;
        mBookPresenter.attachView(this);
        initView();
        initEvent();
    }

    private void initEvent() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword=edtKeyword.getText().toString().trim();
                if (!TextUtils.isEmpty(keyword)){
                    mBookPresenter.getSearchBooks(keyword, null, 0, 20);
                }

            }
        });
    }

    private void initView() {
        edtKeyword = (EditText) findViewById(R.id.edt_keyword);
        button = (Button) findViewById(R.id.button);
        lisview = (ListView) findViewById(R.id.lisview);
        lisview.setAdapter(myAdapter);
    }

    @Override
    public void onSuccess(Book book) {
        books.clear();
        books.addAll(book.getBooks());
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void onError(String result) {
        Toast.makeText(MainActivity.this,result, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBookPresenter.onStop();
    }

    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return books.size();
        }

        @Override
        public Object getItem(int i) {
            return books.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder=null;
            if (view==null){
                viewHolder=new ViewHolder();
                view= LayoutInflater.from(MainActivity.this).inflate(R.layout.item_book,viewGroup,false);
                viewHolder.author = (TextView) view.findViewById(R.id.author);
                viewHolder.price = (TextView) view.findViewById(R.id.price);
                viewHolder.chubanshe = (TextView) view.findViewById(R.id.chubanshe);
                view.setTag(viewHolder);
            }else {
                viewHolder= (ViewHolder) view.getTag();
            }
            Book.BooksBean booksBean=books.get(i);
            viewHolder.author.setText(booksBean.getAuthor_intro());
            viewHolder.price.setText(booksBean.getPrice());
            viewHolder.chubanshe.setText(booksBean.getPublisher());
            return view;
        }

    }
    static class ViewHolder{
        TextView author;
        TextView price;
        TextView chubanshe;
    }
}
