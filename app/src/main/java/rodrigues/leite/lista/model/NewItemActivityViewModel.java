package rodrigues.leite.lista.model;

import android.net.Uri;

import androidx.lifecycle.ViewModel;

public class NewItemActivityViewModel extends ViewModel {
    Uri selectedPhotoLocation = null; //criamos a variavel da classe Uri

    public Uri getSelectedPhotoLocation() {
        return selectedPhotoLocation; //Obtem o item na lista
    }

    public void setSelectedPhotoLocation(Uri selectedPhotoLocation){
        this.selectedPhotoLocation = selectedPhotoLocation; //Seta o endereço uri no View model
    }
}

