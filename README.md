Estructura 
<img width="387" height="528" alt="image" src="https://github.com/user-attachments/assets/61e8adcd-8408-45d8-b820-8e3c9359d18f" />


# 🌌 SWAPI Explorer GUI

**SWAPI Explorer GUI** es una aplicación desarrollada en **Java Swing** que permite consultar información del universo de **Star Wars** usando la [Star Wars API (SWAPI)]<br>(https://swapi.dev/).  
Cuenta con una interfaz moderna de estilo oscuro, búsqueda por categorías y renderizado en tiempo real de resultados JSON.

---

## 🚀 Características principales

- 🌑 Interfaz gráfica moderna con tema oscuro.
- 🔍 Búsqueda en tiempo real en distintas categorías:
  - Films 🎬  
  - People 🧑‍🚀  
  - Planets 🪐  
  - Starships 🚀  
  - Vehicles 🚗  
  - Species 👽  
- 🧠 Uso de `HttpClient` y `Gson` para consumir y parsear datos JSON.
- 🪄 Organización con `GridBagLayout` y `BorderLayout` para una UI adaptable.
- 💾 Código modular, limpio y mantenible en una sola clase.

---

## 🧰 Tecnologías utilizadas

| Tecnología | Descripción |
|-------------|-------------|
| **Java 17+** | Lenguaje base del proyecto |
| **Swing** | Librería para la interfaz gráfica |
| **Gson** | Conversión de JSON a objetos Java |
| **HttpClient (java.net.http)** | Cliente HTTP nativo de Java para consumir la API |
| **SWAPI** | API pública con datos del universo Star Wars |

---

## 🖥️ Captura de pantalla

![Interfaz SWAPI Explorer](./docs/screenshot.png)
modo<br>
<img width="290" height="165" alt="modoseleccion" src="https://github.com/user-attachments/assets/91311c84-a5dd-4f73-a0df-3b9ed156a387" /><br>
consola<br>
<img width="346" height="376" alt="Film1" src="https://github.com/user-attachments/assets/f36375f7-ae5b-41e0-95b5-1cab443dabfb" /><br>
<img width="340" height="401" alt="Characters1" src="https://github.com/user-attachments/assets/66957b9d-1cd0-4099-947b-98992f40a6fc" /><br>
<img width="328" height="376" alt="Planets" src="https://github.com/user-attachments/assets/7e370bb0-aa14-496a-87f3-3e0e46440680" /><br>
<img width="440" height="364" alt="Starships" src="https://github.com/user-attachments/assets/8f3d7dd7-e356-48ed-a0a7-bcb854404657" /><br>
<img width="333" height="366" alt="vehicles" src="https://github.com/user-attachments/assets/5f69949b-e6da-4189-91dc-bda9efc140e0" /><br>
<img width="336" height="362" alt="species" src="https://github.com/user-attachments/assets/f7929396-c34a-443d-8bc0-65ad2043fac1" /><br>
<img width="463" height="300" alt="salir" src="https://github.com/user-attachments/assets/ae8b9d08-d53e-42d3-b96c-d6215eb0d36f" /><br>
interfaz grafica<br>
<img width="744" height="549" alt="filmsg" src="https://github.com/user-attachments/assets/36954b7e-6cc8-44e5-bdea-617a061a3658" /><br>
<img width="737" height="545" alt="peopleg" src="https://github.com/user-attachments/assets/9b64e7c8-25e2-42d3-b6de-88ab4f212019" /><br>
Excepcion<br>
<img width="351" height="248" alt="exception" src="https://github.com/user-attachments/assets/1bff89a1-8478-4dd4-8cbb-574100a9a06c" /><br>
<img width="740" height="547" alt="planetsg" src="https://github.com/user-attachments/assets/c524e52a-bde4-414f-82bd-5b0d7f6f4576" /><br>
<img width="735" height="548" alt="starshipsg" src="https://github.com/user-attachments/assets/2084846c-d843-4bdd-9f4a-fd8562831f80" /><br>
<img width="741" height="545" alt="vehiclesg" src="https://github.com/user-attachments/assets/f552e67a-294d-41b1-8aec-7b7448c5a02d" /><br>
<img width="738" height="546" alt="speciesg" src="https://github.com/user-attachments/assets/a2bc8849-3809-4dd0-8e52-f9cd91770650" /><br>



 *El botón “Buscar” se centra automáticamente y cambia de color al pasar el ratón (hover).*

---
## Libreria gson jar
javac -d bin -cp "lib/gson-2.10.1.jar" src/com/alura/screenmatch/principal/SwapiExplorerGUI.java

git clone https://github.com/tu-usuario/swapi-explorer-gui.git
cd swapi-explorer-gui
