package org.wordpress.android.ui.accounts.signup;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import org.wordpress.android.R;
import org.wordpress.android.ui.WPBottomSheetDialog;

public class SignupBottomSheetDialog extends WPBottomSheetDialog {
    public SignupBottomSheetDialog(@NonNull final Context context,
                                   @NonNull final SignupSheetListener signupSheetListener) {
        super(context);
        //noinspection InflateParams
        final View layout = LayoutInflater.from(context).inflate(R.layout.signup_bottom_sheet_dialog, null);

        Button termsOfServiceText = (Button) layout.findViewById(R.id.signup_tos);
        termsOfServiceText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupSheetListener.onSignupSheetTermsOfServiceClicked();
            }
        });
        termsOfServiceText.setText(Html.fromHtml(String.format(
                context.getResources().getString(R.string.signup_terms_of_service_text), "<u>", "</u>")));

        Button signupWithEmailButton = (Button) layout.findViewById(R.id.signup_email);
        signupWithEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupSheetListener.onSignupSheetEmailClicked();
            }
        });

        Button signupWithGoogleButton = (Button) layout.findViewById(R.id.signup_google);
        signupWithGoogleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signupSheetListener.onSignupSheetGoogleClicked();
            }
        });

        setOnCancelListener(new OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                signupSheetListener.onSignupSheetCanceled();
            }
        });

        setContentView(layout);

        // Set peek height to full height of view to avoid signup buttons being off screen when
        // bottom sheet is shown with small screen height (e.g. landscape orientation).
        final BottomSheetBehavior behavior = BottomSheetBehavior.from((View) layout.getParent());
        setOnShowListener(new OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                behavior.setPeekHeight(layout.getHeight());
            }
        });
    }

    public interface SignupSheetListener {
        void onSignupSheetCanceled();

        void onSignupSheetEmailClicked();

        void onSignupSheetGoogleClicked();

        void onSignupSheetTermsOfServiceClicked();
    }
}
