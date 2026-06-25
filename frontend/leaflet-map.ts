import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

declare global {
    interface Window {
        sunDialMap: {
            setView: (lat: number, lng: number, zoom?: number) => void;
            setMarker: (lat: number, lng: number) => void;
        } | undefined;
    }
}

const host = document.getElementById('map-host');

// Prüfen, ob das Element existiert und die Map noch nicht initialisiert wurde
if (host && !window.sunDialMap) {
    const map = L.map(host).setView([47.3769, 8.5417], 6);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; OpenStreetMap contributors',
    }).addTo(map);

    let marker = L.marker([47.3769, 8.5417]).addTo(map);

    // Hier wurden die Typen (: number) für die Parameter hinzugefügt
    window.sunDialMap = {
        setView(lat: number, lng: number, zoom?: number) {
            map.setView([lat, lng], zoom);
        },
        setMarker(lat: number, lng: number) {
            marker.setLatLng([lat, lng]);
        },
    };
}