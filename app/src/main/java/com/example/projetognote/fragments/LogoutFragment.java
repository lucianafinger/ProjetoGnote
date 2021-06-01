package com.example.projetognote.fragments;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetognote.R;
import com.example.projetognote.activity.MainActivity;

public class LogoutFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        this.logout();
        return null;
    }

    private void logout(){
        new AlertDialog.Builder(getActivity())
                .setIcon(R.drawable.ic_logout)
                .setTitle("Logout")
                .setMessage("Tem certeza que deseja sair?")
                .setPositiveButton("Sim", ((dialog, which) -> getActivity().finish()))
                .setNegativeButton("Não", null)
                .show();
    }
}