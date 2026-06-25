# sun-dial-map-vaadin

Vaadin 24 + Spring Boot starter for rebuilding the original React/TanStack/Leaflet sun-dial map as a Java-based application with free Vaadin components.

## Stack

- Java 21
- Spring Boot 3
- Vaadin 24
- Free Vaadin components only
- Leaflet embedded via frontend modules
- Weather/cloud overlay prepared for later TypeScript canvas rendering

## Current scope

This starter includes:

- Main layout with map panel and control panel
- Coordinate input
- Date/time selection
- Mode switching for sun, moon, and clouds
- Cloud layer selection
- Info panel for calculated values
- Leaflet map placeholder bridge
- Java astronomy service skeleton

## Run locally

```bash
./mvnw spring-boot:run
```
