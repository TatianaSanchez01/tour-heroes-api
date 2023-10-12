package co.edu.udea.api.controller;

import co.edu.udea.api.model.Hero;
import co.edu.udea.api.service.HeroService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/heroes")
public class HeroController {

    private final Logger log = LoggerFactory.getLogger(HeroController.class);

    public HeroService heroService;

    public HeroController(HeroService heroService){
        this.heroService = heroService;
    }

    /**
     * Buscar heroe en la base de datos por id
     * @param id
     * @return Heroe
     */

    @GetMapping("{id}")
    @ApiOperation(value = "Buscar un heroes por su id", response = Hero.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Héroe encontrado exitosamente."),
            @ApiResponse(code = 400, message = "Petición inválida. Asegúrate de proporcionar un ID válido para buscar al héroe."),
            @ApiResponse(code = 404, message = "Héroe no encontrado. No se encontró un héroe con el ID especificado en la base de datos."),
            @ApiResponse(code = 500, message = "Error interno para procesar respuesta. Se ha producido un error interno al intentar buscar al héroe. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<Hero> getHero(@PathVariable Integer id){
        log.info("Rest request search heroe by id: "+ id);
        return ResponseEntity.ok(heroService.getHero(id));
    }

    /**
     * Listar heroes creados en la base de datos
     * @return List<Heroe>
     */

    @GetMapping("")
    @ApiOperation(value = "Buscar todos los heroes", response = List.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Héroes encontrados exitosamente."),
            @ApiResponse(code = 400, message = "Petición inválida. La solicitud no es válida para listar los héroes."),
            @ApiResponse(code = 404, message = "No fue posible listar los héroes, no fueron encontrados."),
            @ApiResponse(code = 500, message = "Error interno para procesar respuesta. Se ha producido un error interno al intentar listar los héroes. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<List<Hero>> getHeroes(){
        log.info("Rest request list heroes");
        return ResponseEntity.ok(heroService.getHeroes());
    }

    /**
     * Almacena un nuevo héroe en la base de datos.
     * @param hero El héroe que se va a almacenar.
     * @return El héroe almacenado exitosamente.
     */
    @PostMapping("")
    @ApiOperation(value = "Agregar un nuevo héroe", response = Hero.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Héroe almacenado exitosamente."),
            @ApiResponse(code = 400, message = "Petición inválida. Asegúrate de proporcionar datos válidos para agregar un nuevo héroe."),
            @ApiResponse(code = 404, message = "No fue posible almacenar el héroe en la base de datos."),
            @ApiResponse(code = 500, message = "Error interno para procesar respuesta. Se ha producido un error interno al intentar almacenar el héroe. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<Hero> addHero(@RequestBody Hero hero){
        log.info("Rest request add new hero");
        return ResponseEntity.ok(heroService.addHero(hero));
    }

    /**
     * Actualizar un héroe en la base de datos.
     * @param hero El héroe con los datos actualizados.
     * @return El héroe actualizado.
     */
    @PutMapping()
    @ApiOperation(value = "Actualizar héroe", response = Hero.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Héroe actualizado exitosamente."),
            @ApiResponse(code = 400, message = "Petición inválida. Asegúrate de proporcionar datos válidos para actualizar un héroe."),
            @ApiResponse(code = 404, message = "No fue posible encontrar un héroe con el ID especificado."),
            @ApiResponse(code = 500, message = "Error interno para procesar respuesta. Se ha producido un error interno al intentar actualizar el héroe. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<Hero> updateHero(@RequestBody Hero hero){
        log.info("Rest request update heroes");
        return ResponseEntity.ok(heroService.updateHero(hero));
    }

    /**
     * Elimina un héroe de la base de datos por ID.
     * @param id El ID del héroe que se va a eliminar.
     * @return ResponseEntity sin cuerpo con el código de respuesta apropiado.
     */
    @DeleteMapping("{id}")
    @ApiOperation(value = "Eliminar héroe por ID", response = Hero.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Héroe eliminado exitosamente."),
            @ApiResponse(code = 400, message = "Petición inválida. Asegúrate de proporcionar un ID válido para eliminar un héroe."),
            @ApiResponse(code = 404, message = "No fue posible eliminar el héroe, no fue encontrado."),
            @ApiResponse(code = 500, message = "Error interno para procesar respuesta. Se ha producido un error interno al intentar eliminar el héroe. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<Void> deteleteHero(@PathVariable Integer id){
        log.info("Rest request delete heroe = {}", id);
        heroService.deleteHero(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Buscar un héroe en la base de datos de acuerdo a un nombre.
     * @param name El nombre del héroe a buscar.
     * @return Una lista de héroes que coinciden con el nombre especificado.
     */
    @GetMapping("/name")
    @ApiOperation(value = "Buscar héroes por nombre", response = List.class)
    @ApiResponses(value= {
            @ApiResponse(code = 200, message = "Héroes encontrados exitosamente."),
            @ApiResponse(code = 400, message = "Petición inválida. Asegúrate de proporcionar un nombre válido para buscar héroes."),
            @ApiResponse(code = 404, message = "No existen héroes con ese nombre."),
            @ApiResponse(code = 500, message = "Error interno para procesar respuesta. Se ha producido un error interno al intentar buscar héroes por nombre. Por favor, intenta nuevamente más tarde."),
    })
    public ResponseEntity<List<Hero>> searchHeroes(@PathVariable String name){
        log.info("Rest request search heroes by name: {}", name);
        return ResponseEntity.ok(heroService.searchHeroes(name));
    }

}
