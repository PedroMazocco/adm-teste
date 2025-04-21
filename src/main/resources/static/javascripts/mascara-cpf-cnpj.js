var Adm = Adm || {};

Adm.MascaraCpfCnpj = (function () {

	function MascaraCpfCnpj() {
		this.radioTipoPessoa = $('.js-radio-tipo-pessoa');
		this.labelCpfCnpj = $('label[for="cpfOuCnpj"]');
		this.inputCpfCnpj = $('#cpfOuCnpj');
	}

	MascaraCpfCnpj.prototype.iniciar = function () {
		this.radioTipoPessoa.on('change', onTipoPessoaAlterado.bind(this));

		var tipoSelecionado = this.radioTipoPessoa.filter(':checked');
		if (tipoSelecionado.length > 0) {
			onTipoPessoaAlterado.call(this, { currentTarget: tipoSelecionado[0] });
		}
	}

	function onTipoPessoaAlterado(evento) {
		var tipoPessoaSelecionada = $(evento.currentTarget);

		var documento = tipoPessoaSelecionada.data('documento');
		var mascara = tipoPessoaSelecionada.data('mascara');


		if (this.labelCpfCnpj.length) {
			this.labelCpfCnpj.text(documento);
		}

		if (this.inputCpfCnpj.length && mascara) {
			this.inputCpfCnpj.mask(mascara);
			this.inputCpfCnpj.removeAttr('disabled');
		}
	}

	return MascaraCpfCnpj;

}());

$(function () {
	var mascaraCpfCnpj = new Adm.MascaraCpfCnpj();
	mascaraCpfCnpj.iniciar();
});
