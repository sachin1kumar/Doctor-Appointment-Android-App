package com.doctor.doctorsappointment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.junit.runners.model.InitializationError;
import org.robolectric.RobolectricTestRunner;

public class MyTestRunner extends RobolectricTestRunner
{
    public MyTestRunner( Class<?> testClass ) throws InitializationError
    {
        super( testClass );
    }

    public static void startFragment( Fragment fragment )
    {
        FragmentManager fragmentManager = new FragmentActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add( fragment, null);
        fragmentTransaction.commit();
    }
}
