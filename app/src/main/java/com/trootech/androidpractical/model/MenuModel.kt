import com.google.gson.annotations.SerializedName

data class MenuModel (
	@SerializedName("data") var data : List<Data>
){

	data class Data (
		@SerializedName("modificadores") val modificadores : List<Modificadores>,
		@SerializedName("idmenu") val idmenu : Int,
		@SerializedName("precioSugerido") val precioSugerido : Double,
		@SerializedName("nombre") val nombre : String,
		@SerializedName("imagen") val imagen : String,
		@SerializedName("codigo") val codigo : String,
		@SerializedName("impuesto") val impuesto : Int,
		@SerializedName("codigoBarra") val codigoBarra : String,
		@SerializedName("precio_abierto") val precio_abierto : Boolean,
		@SerializedName("comision") val comision : Double,
		@SerializedName("tipo_comision") val tipo_comision : Int,
		@SerializedName("descTipoComision") val descTipoComision : String,
		@SerializedName("impuestoAplicado") val impuestoAplicado : Boolean,
		@SerializedName("tipo") val tipo : Int,
		@SerializedName("tipo_desc") val tipo_desc : String,
		@SerializedName("descripcion") val descripcion : String,
		@SerializedName("permite_descuentos") val permite_descuentos : Boolean,
		@SerializedName("categoria") val categoria : Categoria
	){

		data class Categoria (
			@SerializedName("idcategoriamenu") val idcategoriamenu : Int,
			@SerializedName("nombremenu") val nombremenu : String,
			@SerializedName("porcentaje") val porcentaje : Double,
			@SerializedName("impuesto") val impuesto : Double,
			@SerializedName("codigo") val codigo : String,
			@SerializedName("orden") val orden : Int,
			@SerializedName("printers") val printers : List<Printers>
		){
			data class Printers (
				@SerializedName("id_printer") val id_printer : Int,
				@SerializedName("desc_printer") val desc_printer : String,
				@SerializedName("ip") val ip : String,
				@SerializedName("idtipo") val idtipo : Int,
				@SerializedName("desc_tipo") val desc_tipo : String,
				@SerializedName("isDouble") val isDouble : Boolean,
				@SerializedName("cutPaper") val cutPaper : Boolean
			)
		}
		data class Modificadores (
			@SerializedName("idmodificador") val idmodificador : Int
		)
	}
}




