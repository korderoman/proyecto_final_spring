package org.grupo3.proyectofinalspring.infraestructure.adapter;

import lombok.RequiredArgsConstructor;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.CaracteristicasDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.dto.ProductoDTO;
import org.grupo3.proyectofinalspring.domain.aggregates.request.RequestProducto;
import org.grupo3.proyectofinalspring.domain.ports.on.ProductoServiceOut;
import org.grupo3.proyectofinalspring.infraestructure.entity.CaracteristicasEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.CategoriaEntity;
import org.grupo3.proyectofinalspring.infraestructure.entity.ProductoEntity;
import org.grupo3.proyectofinalspring.infraestructure.repository.CaracteristicasRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.CategoriaRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.PrecioRepository;
import org.grupo3.proyectofinalspring.infraestructure.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoAdapter implements ProductoServiceOut {
    private final CategoriaRepository categoriaRepository;
    private final CaracteristicasRepository caracteristicasRepository;
    private final ProductoRepository productoRepository;
    @Override
    public ProductoDTO addProductOut(RequestProducto requestProducto) throws Exception {
        List<CaracteristicasEntity>caracteristicasEntities = getCaracteristicasToEntity(requestProducto.getCaracteristicas());
        CategoriaEntity categoriaEntity = getCategoria(requestProducto.getCategoria());
        ProductoEntity productoEntity = ProductoEntity.builder()
                .empresa(requestProducto.getEmpresa())
                .desc_corta_prod(requestProducto.getDescCortaProd())
                .desc_larga_prod(requestProducto.getDescLargaProd())
                .marca(requestProducto.getMarca())
                .modelo(requestProducto.getModelo())
                .fec_ini_vlg(getTimestamp())
                .fec_fin_vlg(new Timestamp(System.currentTimeMillis()+2*24*600*1000))
                .estado(requestProducto.getEstado())
                .caracteristicas(caracteristicasEntities)
                .categoria(categoriaEntity)
                .precio(requestProducto.getPrecio())
                .usuaCrea("Henry Medina")
                .dateCreate(getTimestamp())
                .build();
        ProductoEntity producto = productoRepository.save(productoEntity);
        return setProductoEntityToProductoDto(producto);
    }

    private ProductoDTO setProductoEntityToProductoDto(ProductoEntity productoEntity){
        return  ProductoDTO.builder()
                .idProducto(productoEntity.getId_producto())
                .descLargaProd(productoEntity.getDesc_larga_prod())
                .descCortaProd(productoEntity.getDesc_corta_prod())
                .empresa(productoEntity.getEmpresa())
                .marca(productoEntity.getMarca())
                .modelo(productoEntity.getModelo())
                .precio(productoEntity.getPrecio())
                .estado(productoEntity.getEstado())
                .caracteristicas(getCaracteristicasToDto(productoEntity.getCaracteristicas()))
                .categoria(productoEntity.getCategoria().getDescripcion())
                .build();

    }

    private CategoriaEntity getCategoria(Long categoriaId) throws Exception {
        Optional<CategoriaEntity> categoriaEntity = categoriaRepository.findById(categoriaId);
        if(categoriaEntity.isPresent()){
            return categoriaEntity.get();
        }
        throw  new Exception("501");
    }

    private List<String> getCaracteristicasToDto(List<CaracteristicasEntity> caracteristicasId){
        List<String> descriptions = new ArrayList<>();
        //* Revisamos el conjunto de características
        for(CaracteristicasEntity caracteristicas:caracteristicasId){
            descriptions.add(caracteristicas.getDescripcion());
            }
        return  descriptions;
    }

    private List<CaracteristicasEntity> getCaracteristicasToEntity(List<Long> caracteristicasId){
        List<CaracteristicasEntity> descriptions = new ArrayList<>();
        for(Long id:caracteristicasId){
            Optional<CaracteristicasEntity> caracteristicasEntity = caracteristicasRepository.findById(id);
            if(caracteristicasEntity.isPresent()){
                descriptions.add(caracteristicasEntity.get());
            }
        }
        return  descriptions;

    }




    @Override
    public List<ProductoDTO> getAllProductsOut() {
        List<ProductoEntity> productoEntities = productoRepository.findAll();
        List<ProductoDTO> productoDTOS = new ArrayList<>();
        for(ProductoEntity p:productoEntities){
            productoDTOS.add(setProductoEntityToProductoDto(p));
        }
        return productoDTOS;
    }

    @Override
    public Optional<ProductoDTO> getProductByIdOut(Long id) {
        Optional<ProductoEntity> productoEntity = productoRepository.findById(id);
        return  productoEntity.map(this::setProductoEntityToProductoDto);
    }

    @Override
    public ProductoDTO updateProductByIdOut(Long id, RequestProducto requestProducto) throws Exception{
        Optional<ProductoEntity> productoEntity = productoRepository.findById(id);
        if(productoEntity.isEmpty()){
            throw  new Exception("502");
        }
        productoEntity.get().setDesc_larga_prod(requestProducto.getDescLargaProd());
        productoEntity.get().setDesc_corta_prod(requestProducto.getDescCortaProd());
        productoEntity.get().setEmpresa(requestProducto.getEmpresa());
        productoEntity.get().setMarca(requestProducto.getMarca());
        productoEntity.get().setModelo(requestProducto.getModelo());
        productoEntity.get().setPrecio(requestProducto.getPrecio());
        productoEntity.get().setUsuaModif("Henry Medina");
        productoEntity.get().setDateModif(getTimestamp());

        return setProductoEntityToProductoDto(productoEntity.get());
    }

    @Override
    public ProductoDTO deleteProductByIdOut(Long id) throws Exception {
        Optional<ProductoEntity> productoEntity = productoRepository.findById(id);
        if(productoEntity.isEmpty()){
            throw new Exception("503");
        }
        productoEntity.get().setEstado(0);
        productoEntity.get().setUsuaDelet("Henry Medina");
        productoEntity.get().setDateDelet(getTimestamp());
        return setProductoEntityToProductoDto(productoEntity.get());
    }


    private Timestamp getTimestamp(){
        long currentTime = System.currentTimeMillis();
        return new Timestamp(currentTime);
    }
}
