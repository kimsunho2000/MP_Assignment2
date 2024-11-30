package com.example.assignment2.ui.slideshow;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.assignment2.R;
import com.example.assignment2.adapters.ImagePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private ViewPager2 viewPager;
    private EditText inputNumber;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);

        viewPager = root.findViewById(R.id.view_pager);
        inputNumber = root.findViewById(R.id.input_number);

        // 이미지 목록 초기화
        List<Integer> images = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            int id = getResources().getIdentifier("image" + i, "drawable", getContext().getPackageName());
            images.add(id);
        }

        // ViewPager2에 어댑터 설정
        viewPager.setAdapter(new ImagePagerAdapter(images));

        // ViewPager2 페이지 변경 시 EditText 내용 업데이트
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                inputNumber.setText(String.valueOf(position + 1)); // 현재 페이지 번호 업데이트
            }
        });

        // EditText에서 엔터 키 입력 시 페이지 이동
        inputNumber.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                String input = inputNumber.getText().toString();
                try {
                    int pageNumber = Integer.parseInt(input);
                    if (pageNumber >= 1 && pageNumber <= images.size()) {
                        viewPager.setCurrentItem(pageNumber - 1); // 입력된 페이지로 이동
                    } else {
                        Toast.makeText(getContext(), "Please enter a digit number between 1 ~ 30", Toast.LENGTH_SHORT).show();
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getContext(), "Please enter a digit number between 1 ~ 30", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
            return false;
        });

        return root;
    }
}