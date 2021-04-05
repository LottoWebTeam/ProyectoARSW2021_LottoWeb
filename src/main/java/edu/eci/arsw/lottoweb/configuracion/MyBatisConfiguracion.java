package edu.eci.arsw.lottoweb.configuracion;

import edu.eci.arsw.lottoweb.persistencia.*;
import edu.eci.arsw.lottoweb.persistencia.mybatis.*;
import edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers.*;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.inject.Named;
import javax.sql.DataSource;

@Configuration
@MapperScan( basePackages = "edu.eci.arsw.lottoweb.persistencia.mybatis.Mappers")
public class MyBatisConfiguracion {
    
    private static final String PRINCIPAL_SESSION_FACTORY = "principalSessionFactory";


    @Bean(name = PRINCIPAL_SESSION_FACTORY, destroyMethod = "")
    @Primary
    public SqlSessionFactoryBean sqlSessionFactoryPrimary(@Named(BaseDeDatos.PRIMARY_DATASOURCE) final DataSource principalDataSource) throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(principalDataSource);
        SqlSessionFactory sqlSessionFactory;
        sqlSessionFactory = sqlSessionFactoryBean.getObject();
        sqlSessionFactory.getConfiguration().addMapper(ClienteMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(ConductorMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(VehiculoMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(ViajeMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(OfertaMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(RutaMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(UbicacionMapper.class);
        sqlSessionFactory.getConfiguration().addMapper(ViajeEnCursoMapper.class);
        // Various other SqlSessionFactory settings
        return sqlSessionFactoryBean;
    }

    @Bean
    @Primary
    public MapperFactoryBean<ClienteMapper> clienteMapper(@Named(PRINCIPAL_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
        MapperFactoryBean<ClienteMapper> factoryBean = new MapperFactoryBean<>(ClienteMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean
    @Primary
    public MapperFactoryBean<VehiculoMapper> vehiculoMapper(@Named(PRINCIPAL_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
        MapperFactoryBean<VehiculoMapper> factoryBean = new MapperFactoryBean<>(VehiculoMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean
    @Primary
    public MapperFactoryBean<ConductorMapper> ConductorMapper(@Named(PRINCIPAL_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
        MapperFactoryBean<ConductorMapper> factoryBean = new MapperFactoryBean<>(ConductorMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean
    @Primary
    public MapperFactoryBean<ViajeMapper> viajeMapper(@Named(PRINCIPAL_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
        MapperFactoryBean<ViajeMapper> factoryBean = new MapperFactoryBean<>(ViajeMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean
    @Primary
    public MapperFactoryBean<OfertaMapper> subastaMapper(@Named(PRINCIPAL_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
        MapperFactoryBean<OfertaMapper> factoryBean = new MapperFactoryBean<>(OfertaMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean
    @Primary
    public MapperFactoryBean<RutaMapper> rutaMapper(@Named(PRINCIPAL_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
        MapperFactoryBean<RutaMapper> factoryBean = new MapperFactoryBean<>(RutaMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean
    @Primary
    public MapperFactoryBean<UbicacionMapper> ubicacionMapper(@Named(PRINCIPAL_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
        MapperFactoryBean<UbicacionMapper> factoryBean = new MapperFactoryBean<>(UbicacionMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean
    @Primary
    public MapperFactoryBean<ViajeEnCursoMapper> viajeEnCursoMapper(@Named(PRINCIPAL_SESSION_FACTORY) final SqlSessionFactoryBean sqlSessionFactoryBean) throws Exception{
        MapperFactoryBean<ViajeEnCursoMapper> factoryBean = new MapperFactoryBean<>(ViajeEnCursoMapper.class);
        factoryBean.setSqlSessionFactory(sqlSessionFactoryBean.getObject());
        return factoryBean;
    }

    @Bean
    @Primary
    public ClienteDao clienteDao(){
        return new MyBatisClienteDao();
    }

    @Bean
    @Primary
    public VehiculoDao vehiculoDao(){
        return new MyBatisVehiculoDao();
    }

    @Bean
    @Primary
    public ConductorDao conductorDao(){
        return new MyBatisConductorDao();
    }

    @Bean
    @Primary
    public ViajeDao ViajeDao(){
        return new MyBatisViajeDao();
    }

    @Bean
    @Primary
    public OfertaDao subastaDao(){
        return new MyBatisOfertaDao();
    }

    @Bean
    @Primary
    public RutaDao rutaDao(){
        return new MyBatisRutaDao();
    }

    @Bean
    @Primary
    public UbicacionDao ubicacionDao(){
        return new MyBatisUbicacionDao();
    }

    @Bean
    @Primary
    public ViajeEnCursoDao ViajeEnCursoDao(){
        return new MyBatisViajeEnCursoDao();
    }


}
