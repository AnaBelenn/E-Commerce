# E-Commerce
## API para un E-commerce

### Entidades:
#### Usuario
Datos correspondientes al usuario.
* Nombre
* Apellido
* Direccion
* Email
* Contraseña
* Pais
* Provincia
* Ciudad
* FechaCreacion
* Carritos


#### Producto
Datos correspondientes al producto.
* Nombre
* Descripcion
* Precio
* Contenido
* FechaCreacion
* Publicado

#### Carrito
Datos correspondientes al carrito.
* Usuario
* LineasCarrito
* FechaCreacion
* FechaModificacion
* GeneradoPor (device)
* Estado (activo/inactivo)

#### LineaCarrito
Datos de una linea/detalle de compra.
* Carrito
* Producto
* Cantidad
* PrecioUnitario

#### Orden
Datos de una orden de compra ya cerrada.
* Usuario
* IdCarrito
* LineasCarrito
* FechaCreacion
* Observación


### EndPointS:

**Usuario**
- GET /usuario devuelve todos los usuarios.
- GET /usuario/{id} devuelve los datos del usuario correspondiente al id indicado.
- POST /usuario crea un usuario
- PUT /usuario/{id} modifica los atributos indicados del usuario correspondiente al id. 
- DELETE /usuario/{id} borra el usuario correspondiente al id.

**Producto**
- GET /producto devuelve todos los productos.
- GET /producto/{id} devuelve la informacion del producto correspondiente al id indicado.
- POST /producto crea un producto.
- PUT /producto/{id} modifica los atributos indicados del producto correspondiente al id. 
- DELETE /producto/{id} borra el producto correspondiente al id.


**Carrito**
- GET /carrito devuelve todos los carritos.
- GET /carrito/{id} devuelve la informacion del carrito correspondiente al id indicado.
- POST /carrito crea un carrito nuevo.
- DELETE /carrito/{idC} borra el carrito (idC).
