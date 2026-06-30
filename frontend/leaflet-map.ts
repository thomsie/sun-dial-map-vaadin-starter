import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

declare global {
    interface Window {
        sunDialMap: {
            initMap: (elementId: string) => void;
            setView: (lat: number, lng: number, zoom?: number) => void;
            setMarker: (lat: number, lng: number) => void;
            updateSunLines: (lat: number, lng: number, sunriseAzimuth: number, currentAzimuth: number, sunsetAzimuth: number) => void;
        } | undefined;
    }
}

let map: L.Map;
let marker: L.Marker;
let sunriseLine: L.Polyline;
let currentSunLine: L.Polyline;
let sunsetLine: L.Polyline;

// Hilfsfunktion, um einen Endpunkt anhand eines Startpunkts, Winkels und einer Distanz (in Metern) zu berechnen
function getPointByAzimuth(lat: number, lng: number, azimuth: number, distanceMeter: number = 50000): [number, number] {
    const angle = (azimuth * Math.PI) / 180;
    const finalLat = lat + (distanceMeter / 111000) * Math.cos(angle); // <-- JETZT MIT "const"
    const finalLng = lng + (distanceMeter / (111000 * Math.cos((lat * Math.PI) / 180))) * Math.sin(angle);
    return [finalLat, finalLng];
}

window.sunDialMap = {
    initMap(elementId: string) {
        const host = document.getElementById(elementId);
        if (!host || map) return;

        map = L.map(host).setView([47.3769, 8.5417], 6);
        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            maxZoom: 19,
            attribution: '&copy; OpenStreetMap contributors',
        }).addTo(map);

        marker = L.marker([47.3769, 8.5417]).addTo(map);

        setTimeout(() => { map.invalidateSize(); }, 100);
    },

    setView(lat: number, lng: number, zoom?: number) {
        if (map) {
            map.setView([lat, lng], zoom);
            map.invalidateSize();
        }
    },

    setMarker(lat: number, lng: number) {
        if (marker) marker.setLatLng([lat, lng]);
    },

    // NEU: Diese Funktion zeichnet die drei Linien auf die Karte
    updateSunLines(lat: number, lng: number, sunriseAzimuth: number, currentAzimuth: number, sunsetAzimuth: number) {
        if (!map) return;

        // Alte Linien entfernen, falls sie existieren
        if (sunriseLine) map.removeLayer(sunriseLine);
        if (currentSunLine) map.removeLayer(currentSunLine);
        if (sunsetLine) map.removeLayer(sunsetLine);

        const startPoint: [number, number] = [lat, lng];

        // Endpunkte für die Linien berechnen (50km lang)
        const sunriseEnd = getPointByAzimuth(lat, lng, sunriseAzimuth, 50000);
        const currentEnd = getPointByAzimuth(lat, lng, currentAzimuth, 50000);
        const sunsetEnd = getPointByAzimuth(lat, lng, sunsetAzimuth, 50000);

        // 1. Sonnenaufgang (Gelb/Orange)
        sunriseLine = L.polyline([startPoint, sunriseEnd], { color: '#ffc107', weight: 4, opacity: 0.8 }).addTo(map);

        // 2. Aktueller Sonnenstand (Rot)
        currentSunLine = L.polyline([startPoint, currentEnd], { color: '#dc3545', weight: 5, opacity: 0.9 }).addTo(map);

        // 3. Sonnenuntergang (Dunkles Orange/Violett)
        sunsetLine = L.polyline([startPoint, sunsetEnd], { color: '#fd7e14', weight: 4, opacity: 0.8 }).addTo(map);
    }
};