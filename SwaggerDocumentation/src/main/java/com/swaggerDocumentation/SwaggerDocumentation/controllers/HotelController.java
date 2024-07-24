package com.swaggerDocumentation.SwaggerDocumentation.controllers;


import com.swaggerDocumentation.SwaggerDocumentation.entities.Hotel;
import com.swaggerDocumentation.SwaggerDocumentation.services.HotelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Operation(summary = "Get all hotels")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the hotels",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Hotel.class)) }),
            @ApiResponse(responseCode = "404", description = "Hotels not found",
                    content = @Content)
    })
    @GetMapping
    public List<Hotel> getAllHotels() {
        return hotelService.getAllHotels();
    }

    @Operation(summary = "Get a hotel by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the hotel",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Hotel.class)) }),
            @ApiResponse(responseCode = "404", description = "Hotel not found",
                    content = @Content)
    })
    @GetMapping("/{id}")
    public Hotel getHotelById(@PathVariable Long id) {
        return hotelService.getHotelById(id);
    }

    @Operation(summary = "Create a new hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hotel created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Hotel.class)) })
    })
    @PostMapping
    public Hotel createHotel(@RequestBody Hotel hotel) {
        return hotelService.createHotel(hotel);
    }

    @Operation(summary = "Update an existing hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hotel updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Hotel.class)) }),
            @ApiResponse(responseCode = "404", description = "Hotel not found",
                    content = @Content)
    })
    @PutMapping("/{id}")
    public Hotel updateHotel(@PathVariable Long id, @RequestBody Hotel hotel) {
        return hotelService.updateHotel(id, hotel);
    }

    @Operation(summary = "Delete a hotel")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Hotel deleted")
    })
    @DeleteMapping("/{id}")
    public void deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
    }
}