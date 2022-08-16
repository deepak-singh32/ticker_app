package com.example.ticker;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.ticker.databinding.LayoutBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class BottomSheet extends BottomSheetDialogFragment {


    BottomSheetBehavior bottomSheetBehavior;
    LayoutBottomSheetBinding bi;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        //inflating layout
        View view = View.inflate(getContext(), R.layout.layout_bottom_sheet, null);

        //binding views to data binding.
        bi = DataBindingUtil.bind(view);

        //setting layout with bottom sheet
        bottomSheet.setContentView(view);

        //setting bottom sheet to cancelable false
        bottomSheet.setCanceledOnTouchOutside(false);




        bottomSheetBehavior = BottomSheetBehavior.from((View) (view.getParent()));


        DisplayMetrics displayMetrics = requireActivity().getResources().getDisplayMetrics();
        int height = displayMetrics.heightPixels;


        int peekHeight = (int) (height*0.30);

        //setting Peek at the 16:9 ratio keyline of its parent.
        bottomSheetBehavior.setPeekHeight(peekHeight);





        int maxHeight = (int) (height*0.90);

        //setting
        bottomSheetBehavior.setMaxHeight(maxHeight);


        //setting max height of bottom sheet
        bi.extraSpace.setMinimumHeight(maxHeight);


        bottomSheetBehavior.setHideable(false);



        bi.fourth.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if((bi.first.getText().toString()+bi.second.getText().toString()+bi.third.getText().toString()+bi.fourth.getText().toString()).length() == 4){
                        startActivity(new Intent(requireActivity(),BusActivity.class));
                    }
                    return true;
                }
                return false;
            }
        });

        bi.busButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bi.busButton2.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(), R.color.colorLightGrey));
                view.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(), R.color.colorBlue));
            }
        });

        bi.busButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                bi.busButton1.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(), R.color.colorLightGrey));
                view.setBackgroundTintList(ContextCompat.getColorStateList(requireActivity(), R.color.colorBlue));
            }
        });

        nextFocus(bi.first, bi.second);
        nextFocus(bi.second,bi.third);
        nextFocus(bi.third,bi.fourth);

        checkDLValid();

        setBottomSheetBehavior(bottomSheetBehavior);


        return bottomSheet;
    }



    private void checkDLValid(){



    }



    private void setBottomSheetBehavior(BottomSheetBehavior behavior){
        bi.first.setOnFocusChangeListener((v,b)->{behavior.setState(BottomSheetBehavior.STATE_EXPANDED);});
        bi.second.setOnFocusChangeListener((v,b)->{behavior.setState(BottomSheetBehavior.STATE_EXPANDED);});
        bi.third.setOnFocusChangeListener((v,b)->{behavior.setState(BottomSheetBehavior.STATE_EXPANDED);});
        bi.fourth.setOnFocusChangeListener((v,b)->{behavior.setState(BottomSheetBehavior.STATE_EXPANDED);});
    }

    private void nextFocus(EditText first , EditText second){
        first.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (first.getText().toString().length() == 1) {

                    first.clearFocus();
                    second.requestFocus();

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    @Override
    public void onCancel(DialogInterface dialog) {

        super.onCancel(dialog);
        requireActivity().finish();

        // Add you codition
    }

    @Override
    public int getTheme() {
        return R.style.BottomSheetDialogTheme;
    }



    @Override
    public void onStart() {
        super.onStart();

        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
    }

    private void hideAppBar(View view) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = 0;
        view.setLayoutParams(params);

    }

    private void showView(View view, int size) {
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.height = size;
        view.setLayoutParams(params);
    }

    private int getActionBarSize() {
        final TypedArray array = getContext().getTheme().obtainStyledAttributes(new int[]{android.R.attr.actionBarSize});
        int size = (int) array.getDimension(0, 0);
        return size;
    }
}