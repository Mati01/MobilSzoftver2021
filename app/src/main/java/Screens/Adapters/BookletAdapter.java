package Screens.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobilszoftver2021.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Model.Booklet;
import Screens.Activities.DetailsActivity;

public class BookletAdapter  extends RecyclerView.Adapter<BookletAdapter.MyViewHolder> {

    Context context;
    public List<Booklet> booklets = new ArrayList<>();

    public BookletAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.booklet_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.titleView.setText(booklets.get(position).getTitle());
        holder.subTitleView.setText(booklets.get(position).getSubtitle());
        holder.authorView.setText(booklets.get(position).getAuthor());
        holder.releaseDateView.setText(new SimpleDateFormat("yyyy-mm-dd").format(booklets.get(position).getReleaseDate()));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("Id", booklets.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return booklets.size();
    }

    public void Remove(int position) {
        booklets.remove(position);
        notifyDataSetChanged();
    }

    public  void SetBooklets(List<Booklet> booklets)
    {
        this.booklets = booklets;
        notifyDataSetChanged();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public  void RemoveById(int id)
    {
        booklets.removeIf( x-> x.getId() == id);
        notifyDataSetChanged();
    }

    public class MyViewHolder extends   RecyclerView.ViewHolder{

        TextView titleView, subTitleView, authorView, releaseDateView;
        ImageView imageView;
        ConstraintLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            titleView = itemView.findViewById(R.id.booklet_title_text);
            subTitleView = itemView.findViewById(R.id.booklet_subtitle_text);
            authorView = itemView.findViewById(R.id.booklet_author_text);
            releaseDateView = itemView.findViewById(R.id.booklet_releaseDate_text);
            imageView = itemView.findViewById(R.id.booklet_imageView);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
