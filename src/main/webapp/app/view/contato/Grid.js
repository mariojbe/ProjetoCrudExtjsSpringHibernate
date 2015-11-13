Ext.define('ExtMVC.view.contato.Grid', {
    extend: 'Ext.grid.Panel',
    alias: 'widget.contatogrid',
    requires: ['Ext.toolbar.Paging'],
    iconCls: 'icon-grid',
    title: 'Contatos',
    store: 'Contatos',
    columns: [{
            header: "NOME",
            width: 170,
            flex: 1,
            dataIndex: 'name'
        }, {
            header: "TELEFONE",
            width: 160,
            flex: 1,
            dataIndex: 'phone'
        }, {
            header: "EMAIL",
            width: 170,
            flex: 1,
            dataIndex: 'email'
        }],
    initComponent: function () {

        this.dockedItems = [{
                xtype: 'toolbar',
                items: [{
                        iconCls: 'icon-save',
                        itemId: 'add',
                        text: 'Adicionar',
                        action: 'add'
                    }, {
                        iconCls: 'icon-delete',
                        text: 'Excluir',
                        action: 'delete'
                    }]
            },
            {
                xtype: 'pagingtoolbar',
                dock: 'top',
                store: 'Contatos',
                displayInfo: true,
                displayMsg: 'Mostrando Contatos {0} - {1} de {2}',
                emptyMsg: "Nenhum contato encontrado."
            }];

        this.callParent(arguments);
    },
    listeners: {
        afterlayout: function (grid) {
            if ((this.store.getCount() == 0)) {
                setTimeout(function () {
                    grid.el.unmask();
                }, 100);
                grid.el.mask('Carregando...');
                this.store.load({
                    callback: function () {
                        grid.el.unmask();
                    }
                });
            }
        }
    }

});
