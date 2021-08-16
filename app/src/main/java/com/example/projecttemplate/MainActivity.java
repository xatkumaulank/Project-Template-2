package com.example.projecttemplate;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.projecttemplate.databinding.ActivityMainBinding;
import com.example.projecttemplate.transformer.DepthPageTransformer;
import com.example.projecttemplate.transformer.ZoomOutPageTransformer;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    ViewPagerAdapter viewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        viewPagerAdapter = new ViewPagerAdapter(this);
        binding.viewPager2.setAdapter(viewPagerAdapter);


        binding.bottomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.bottom_home){
                    binding.viewPager2.setCurrentItem(0);
                }else if (id == R.id.bottom_favorite){
                    binding.viewPager2.setCurrentItem(1);

                }else if (id == R.id.bottom_history){
                    binding.viewPager2.setCurrentItem(2);

                }
                return false;
            }
        });


        binding.viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        binding.bottomNavigation.getMenu().findItem(R.id.bottom_home).setChecked(true);
                        break;
                    case 1:
                        binding.bottomNavigation.getMenu().findItem(R.id.bottom_favorite).setChecked(true);
                        break;
                    case 2:
                        binding.bottomNavigation.getMenu().findItem(R.id.bottom_history).setChecked(true);
                        break;
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.menu_zoom){
            binding.viewPager2.setPageTransformer(new ZoomOutPageTransformer());
        }else if(id == R.id.menu_depth){
            binding.viewPager2.setPageTransformer(new DepthPageTransformer());
        }
        return true;
    }
}