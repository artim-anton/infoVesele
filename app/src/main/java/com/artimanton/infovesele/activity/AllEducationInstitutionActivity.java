package com.artimanton.infovesele.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.artimanton.infovesele.R;
import com.artimanton.infovesele.activity.all_education_institution.FirstSchoolActivity;
import com.artimanton.infovesele.activity.all_education_institution.GymnasiumActivity;
import com.artimanton.infovesele.activity.all_education_institution.LyceumActivity;
import com.artimanton.infovesele.activity.all_education_institution.SecondSchoolActivity;
import com.artimanton.infovesele.activity.all_organization.OrganizationActivity;

public class AllEducationInstitutionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_education_institution);
    }

    public void btnFirstSchool(View view) {
        Intent intent = new Intent(AllEducationInstitutionActivity.this, FirstSchoolActivity.class);
        startActivity(intent);
    }

    public void btnSecondSchool(View view) {
        Intent intent = new Intent(AllEducationInstitutionActivity.this, SecondSchoolActivity.class);
        startActivity(intent);
    }

    public void btnGymnasium(View view) {
        Intent intent = new Intent(AllEducationInstitutionActivity.this, GymnasiumActivity.class);
        startActivity(intent);
    }

    public void btnLyceum(View view) {
        Intent intent = new Intent(AllEducationInstitutionActivity.this, LyceumActivity.class);
        startActivity(intent);
    }
}
