import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

declare global {
    interface Window {
        sunDialMap: {
            initMap: (elementId: string) => void;
            setView: (lat: number, lng: number, zoom?: number) => void;
            setMarker: (lat: number, lng: number) => void;
        } | undefined;
    }
}

let map: L.Map;
let marker: L.Marker;

// ... Ihr restlicher TypeScript-Code (Imports, Interface-Deklaration)

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

        // NEU: Dem Browser eine Millisekunde Zeit geben, dann die Kartengröße korrigieren
        setTimeout(() => {
            map.invalidateSize();
        }, 100);
    },

    setView(lat: number, lng: number, zoom?: number) {
        if (map) {
            map.setView([lat, lng], zoom);
            map.invalidateSize(); // Auch beim Verschieben kurz triggern
        }
    },

    setMarker(lat: number, lng: number) {
        if (marker) marker.setLatLng([lat, lng]);
    }
};