package com.ibrhmdurna.idgames.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ibrhmdurna.idgames.R;
import com.ibrhmdurna.idgames.models.Category;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryViewHolder> {

    private ArrayList<Category> categories;

    public CategoriesAdapter(ArrayList<Category> categories) {
        this.categories = categories;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_layout, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.setData(categories.get(position));
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {

        private RoundedImageView icon;
        private TextView title;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            icon = itemView.findViewById(R.id.category_icon);
            title = itemView.findViewById(R.id.category_title);
        }

        public void setData(Category category){
            icon.setImageDrawable(category.getIcon());
            title.setText(category.getTitle());
        }
    }
}
