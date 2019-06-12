package com.erdemsiyam.memorizeyourwords.service;

import android.content.Context;
import com.erdemsiyam.memorizeyourwords.database.MyDatabase;
import com.erdemsiyam.memorizeyourwords.entity.Category;

import java.util.List;

public final class CategoryService {
    private static List<Category> categories;

    public static List<Category> getCategories(Context context){
        if(categories == null)
            categories = MyDatabase.getMyDatabase(context).getCategoryDAO().getAllCategory();
        return categories;
    }
    public static void updateCategory(Context context, Category updatedCategory){
        MyDatabase.getMyDatabase(context).getCategoryDAO().updateCategory(updatedCategory);
        Category existsCategory = findCategoryInStaticList(updatedCategory);
        if(existsCategory == null) throw new RuntimeException("Category Not Found.");
        existsCategory.setColor(updatedCategory.getColor());
        existsCategory.setName(updatedCategory.getName());
    }
    public static void deleteCategory(Context context, Category willRemoveCategory){
        Category existsCategory = findCategoryInStaticList(willRemoveCategory);
        if(existsCategory == null){throw new RuntimeException("Category Not Found.");} // actually not need we already trying to delete this.
        categories.remove(existsCategory);
        MyDatabase.getMyDatabase(context).getCategoryDAO().deleteCategory(willRemoveCategory);
    }
    public static void addCategory(Context context, Category category){
        category.setId(MyDatabase.getMyDatabase(context).getCategoryDAO().insertCategory(category));
        categories.add(category);
    }
    // buna gerek var mı
    private static Category findCategoryInStaticList(Category willSearchCategory){
        Category existsCategory = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            existsCategory = categories.stream().filter(x -> willSearchCategory.getId().equals(x.getId())).findAny().orElse(null);
        }
        else {
            for (Category c : categories) {
                if(c.getId().equals(willSearchCategory.getId())){
                    existsCategory = c;
                    break;
                }
            }
        }
        return existsCategory;
    }
}
