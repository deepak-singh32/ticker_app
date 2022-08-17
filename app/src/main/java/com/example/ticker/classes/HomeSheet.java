package com.example.ticker.classes;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.example.ticker.BusActivity;
import com.example.ticker.MainActivity;
import com.example.ticker.R;
import com.example.ticker.TicketActivity;
import com.example.ticker.databinding.HomeBottomSheetBinding;
import com.example.ticker.databinding.LayoutBottomSheetBinding;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class HomeSheet extends BottomSheetDialogFragment {

    BottomSheetBehavior bottomSheetBehavior;
    HomeBottomSheetBinding bi;



    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        BottomSheetDialog bottomSheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        //inflating layout
        View view = View.inflate(getContext(), R.layout.home_bottom_sheet, null);

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

        bi.firstlayout.setOnClickListener((v)->{ startActivity(new Intent(requireActivity(), MainActivity.class));});


        return bottomSheet;
    }




    @Override
    public void onCancel(DialogInterface dialog) {

        super.onCancel(dialog);
        requireActivity().finish();
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


}
