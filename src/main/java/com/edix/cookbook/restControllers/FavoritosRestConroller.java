package com.edix.cookbook.restControllers;

import com.edix.cookbook.services.IFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favoritos")
@CrossOrigin("*")
public class FavoritosRestConroller {

    @Autowired
    IFavoritoService fServ;

    /**
     * Este método obtiene todos los favoritos de un usuario por su id
     * @param idUsuario
     * @return ResponseEntity con todos los favoritos
     * @throws Exception
     */
    @GetMapping("/porIdUsuario")
    public ResponseEntity<?> getFavoritosByIdUsuario(@RequestParam int idUsuario) {
        try {
            return ResponseEntity.ok(fServ.findAllFavoritosById(idUsuario));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
