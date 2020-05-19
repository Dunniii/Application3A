package com.example.application3a.presentation.controller;

import com.example.application3a.presentation.model.Atome;
import com.example.application3a.presentation.view.DescriptionActivity;

    public class DescriptionController {
        private Atome atome;
        private DescriptionActivity view;

        public DescriptionController(DescriptionActivity view, Atome atome) {
            this.view = view;
            this.atome= atome;
        }

        public void onStart(){
            view.showDetail(atome);
        }

        public void onButtonClick(){
            view.backToList();
        }
}
