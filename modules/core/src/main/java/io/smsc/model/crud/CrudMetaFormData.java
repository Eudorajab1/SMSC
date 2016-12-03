package io.smsc.model.crud;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "CRUD_META_FORM_DATA")
public class CrudMetaFormData extends CrudPropertyMetaData {

    @Column(name = "FIELD_LAYOUT_GRID_POSITION")
//    @NotEmpty(message = "{crud.meta.form.data.field.layout.grid.position.validation}")
    private String fieldLayoutGridPosition;

    @OneToMany
    @JoinColumn(name="CRUD_META_FORM_DATA", referencedColumnName = "ID")
    private Set<MetaDataPropertyBindingParameter> bindingParameters;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CRUD_CLASS_META_DATA")
    private CrudClassMetaData crudClassMetaData;

    public CrudMetaFormData() {
    }

    public CrudMetaFormData(Long id, String property, Boolean editable, Boolean visible, String decorator, Double order) {
        super(id, property, editable, visible, decorator, order);
    }

    public CrudMetaFormData(CrudMetaFormData crudMetaFormData) {
        this(crudMetaFormData.getId(), crudMetaFormData.getProperty(), crudMetaFormData.getEditable(), crudMetaFormData.getVisible(),
                crudMetaFormData.getDecorator(), crudMetaFormData.getOrder(), crudMetaFormData.getFieldLayoutGridPosition());
    }

    public CrudMetaFormData(Long id, String property, Boolean editable, Boolean visible, String decorator, Double order,
                            String fieldLayoutGridPosition) {
        super(id, property, editable, visible, decorator, order);
        this.fieldLayoutGridPosition = fieldLayoutGridPosition;
    }

    @PreRemove
    private void removeBindingParametersFromCrudMetaMetaData() {
        for(MetaDataPropertyBindingParameter parameter : bindingParameters){
            parameter.setCrudMetaFormDataId(null);
        }
    }

    public String getFieldLayoutGridPosition() {
        return fieldLayoutGridPosition;
    }

    public void setFieldLayoutGridPosition(String fieldLayoutGridPosition) {
        this.fieldLayoutGridPosition = fieldLayoutGridPosition;
    }

    public Set<MetaDataPropertyBindingParameter> getBindingParameters() {
        return bindingParameters;
    }

    public void setBindingParameters(Set<MetaDataPropertyBindingParameter> bindingParameters) {
        this.bindingParameters = bindingParameters;
    }

    public CrudClassMetaData getCrudClassMetaData() {
        return crudClassMetaData;
    }

    public void setCrudClassMetaData(CrudClassMetaData crudClassMetaData) {
        this.crudClassMetaData = crudClassMetaData;
    }

    public boolean addBindingParameter(MetaDataPropertyBindingParameter parameter) {
        return this.getBindingParameters().add(parameter);
    }

    public boolean removeBindingParameter(MetaDataPropertyBindingParameter parameter) {
        return this.getBindingParameters().remove(parameter);
    }

    @Override
    public String toString() {
        return "CrudMetaFormData{" +
                "fieldLayoutGridPosition='" + fieldLayoutGridPosition + '\'' +
                ", bindingParameters=" + bindingParameters +
                ", crudClassMetaFormData=" + crudClassMetaData +
                "} " + super.toString();
    }
}