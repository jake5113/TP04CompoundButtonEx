package com.jake5113.tp04compoundbuttonex;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.RadioGroup;

import com.google.android.material.button.MaterialButtonToggleGroup;
import com.jake5113.tp04compoundbuttonex.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    String name, gender, city, number, contact;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // 등록 버튼 클릭 시
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 이름 get
                name = binding.nameEt.getText().toString();

                // 번호 get
                number = binding.numEt1.getText().toString();
                number += "-" + binding.numEt2.getText().toString();
                number += "-" + binding.numEt3.getText().toString();

                // 연락방법 get
                contact = "";
                if (binding.cb1.isChecked()) contact += binding.cb1.getText().toString();
                if (binding.cb2.isChecked()) contact += binding.cb2.getText().toString();
                if (binding.cb3.isChecked()) contact += binding.cb3.getText().toString();
                if (binding.cb4.isChecked()) contact += binding.cb4.getText().toString();

                binding.showTv.append(name + "," + city + "," + gender + "," + number + "," + contact + "\n");
            }
        });

        // 성별 get
        binding.genderRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                if (id == R.id.male_rb) gender = binding.maleRb.getText().toString();
                else if (id == R.id.female_rb) gender = binding.femaleRb.getText().toString();
            }
        });

        // 주소 get
        binding.cityRg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = group.getCheckedRadioButtonId();
                if (id == R.id.seoul_rb) city = binding.seoulRb.getText().toString();
                else if (id == R.id.busan_rb) city = binding.busanRb.getText().toString();
                else if (id == R.id.etc_rb) city = binding.etcRb.getText().toString();
            }
        });

        // 전화번호 입력시 자동으로 Focus 넘어가는 코드
        binding.numEt1.addTextChangedListener(tw);
        binding.numEt2.addTextChangedListener(tw);

    } //onCreate

    // 전화번호 입력시 자동으로 Focus 넘어가는 코드
    TextWatcher tw = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (getCurrentFocus() == binding.numEt1) {
                if (binding.numEt1.length() >= 3) {
                    binding.numEt2.requestFocus();
                }
            } else if (getCurrentFocus() == binding.numEt2) {
                if (binding.numEt2.length() >= 4) binding.numEt3.requestFocus();
                //TODO 2번째 번호 4개 입력돼 있으면 첫번째 칸 수정시 자동 3번쨰로 이동
            } else if (getCurrentFocus() == binding.numEt3) {

            }
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}
