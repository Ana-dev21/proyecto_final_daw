package com.edix.cookbook.restControllers;

import com.edix.cookbook.services.IFavoritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
            return ResponseEntity.ok(fServ.findAllFavoritosByIdUsuario(idUsuario));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Este método añade una receta favorita a un usuario
     * @param idUsuario
     * @param idReceta
     * @return ResponseEntity con el favorito añadido
     * @throws Exception
     */
    @PostMapping("/add")
    public ResponseEntity<?> addFavorito(@RequestParam int idUsuario, @RequestParam int idReceta) {
        try {
            return new ResponseEntity<>(fServ.addFavorito(idUsuario, idReceta), HttpStatus.CREATED);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método que borra un favorito de la bbdd
     * @param idUsuario
     * @param idReceta
     * @return ResponseEntity con el favorito borrado
     * @throws Exception
     */
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteFavorito(@RequestParam int idUsuario, @RequestParam int idReceta) {
        try {
            return ResponseEntity.ok(fServ.deleteFavorito(idUsuario, idReceta));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/esFavorita")
    public ResponseEntity<?> esFavorita(@RequestParam int idUsuario, @RequestParam int idReceta) {
        System.out.println("idUsuario: " + idUsuario);
        try {
            return ResponseEntity.ok(fServ.isFavorita(idUsuario, idReceta));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
