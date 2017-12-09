/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/* global swal, Pace */

(function ($) {
    $('[data-toggle="tooltip"]').tooltip();
    $(document).ajaxStart(function() { Pace.restart(); });
    $(document).ajaxComplete(function(){ $('table-ajax').dataTable(); });
    
    if ($('.checkbox').length) {
        $('.checkbox').iCheck({
            checkboxClass: 'icheckbox_square-blue',
            radioClass: 'iradio_square-blue',
            increaseArea: '20%'
        });
    }

    $('#recover-password').on('click', function () {
        swal({
            title: "Recuperar contraseña",
            text: "Ingrese su correo en el sistema:",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: false,
            inputPlaceholder: "correo@ejemplo.cl"
        }, function (inputValue) {
            if (inputValue === false) return false;
            if (inputValue === "" || !validateEmail(inputValue)) {
                swal.showInputError("Debe ingresar un correo válido.");
                return false;
            }
            swal({
                title: "Recuperar contraseña",
                text: "Se enviará un correo con las instrucciones, está seguro?",
                type: "info",
                showCancelButton: true,
                closeOnConfirm: false,
                showLoaderOnConfirm: true
            }, function () {
                setTimeout(function () {
                    swal("Correo enviado!", "", "success");
                }, 2000);
            });
        });
    });
    
    $('#registra').on('click', function (e) {
        if (!cem.validForm())
            return;
        else {
            var data = cem.dataFormMantenedor();
            $.ajax({
                url: 'registrarse.htm',
                data: 'action=register' + data,
                type: 'POST',
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Usuario registrado!",
                            text: "",
                            type: "success"
                        },
                                function () {
                                    location.reload();
                                });
                    } else {
                        swal({
                            title: "Error al registrar el usuario!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al registrar el usuario!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        }
        e.preventDefault();
    });

    if ($('#mantenedor').length) {
        $('#mantenedor').DataTable({
            "paging": true,
            "lengthChange": false,
            "searching": true,
            "ordering": true,
            "info": true,
            "autoWidth": false
        });
    }

   if ($('select').length) {
        $('select').select2({
            placeholder: "Seleccione...",
            allowClear: true
        });
    }
    
    $('#region_id').select2().on('change', function() {
        if (this.value > 0) {
            $.ajax({
                url: './provincias',
                data: {region_id: this.value},
                type: 'POST',
                success: function (data) {
                    if (typeof(data.provinces) !== undefined) {
                        $('#province_id').html('').select2({
                            data: data.provinces
                        });
                    } else {
                        swal({
                            title: "Error al buscar provincias!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al buscar provincias!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        }
    });
    
    $('#province_id').select2().on('change', function() {
        if (this.value > 0) {
            $.ajax({
                url: './comunas',
                data: {province_id: this.value},
                type: 'POST',
                success: function (data) {
                    if (typeof(data.districts) !== undefined) {
                        $('#district_id').html('').select2({
                            data: data.districts
                        });
                    } else {
                        swal({
                            title: "Error al buscar comunas!",
                            text: "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al buscar comunas!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        }
    });
    
    $('#newItem').on('click', function () {
        cem.clearFormMantanedor();
        $('#new').modal('show');
    });

    $('#addNew').on('click', function () {
        var data = cem.dataFormMantenedor();
        if (!cem.validForm())
            return;
        //$('#new').modal('hide');
        var url = $(this).attr('data-url');
        swal({
            title: 'Envío de datos',
            text: 'Está seguro de la información ingresada?',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: 'slide-from-top',
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                url: url,
                data: data,
                type: "POST",
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Datos guardados!",
                            text: "",
                            type: "success"
                        },
                        function () {
                            location.reload();
                        });
                    } else {
                        swal({
                            title: "Error al guardar los datos!",
                            text: data.msg || "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al guardar los datos!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        });
    });
    
    $('#saveCurso').on('click', function () {
        var data = cem.dataFormMantenedor('cursoForm');
        if (!cem.validForm('cursoForm'))
            return;
        var url = $(this).attr('data-url');
        var idPrograma = $(this).attr('data-programa');
        swal({
            title: 'Envío de datos',
            text: 'Está seguro de la información ingresada?',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: 'slide-from-top',
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                url: url,
                data: data + '&idPrograma=' + idPrograma,
                type: "POST",
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Datos guardados!",
                            text: "",
                            type: "success"
                        },
                        function () {
                            location.reload();
                        });
                    } else {
                        swal({
                            title: "Error al guardar los datos!",
                            text: data.msg || "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al guardar los datos!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        });
    });
    
    $('#saveNota').on('click', function () {
        var data = cem.dataFormMantenedor('notaForm');
        if (!cem.validForm('notaForm'))
            return;
        var url = $(this).attr('data-url');
        swal({
            title: 'Envío de datos',
            text: 'Está seguro de la información ingresada?',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: 'slide-from-top',
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                url: url,
                data: data,
                type: "POST",
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Datos guardados!",
                            text: "",
                            type: "success"
                        },
                        function () {
                            location.reload();
                        });
                    } else {
                        swal({
                            title: "Error al guardar los datos!",
                            text: data.msg || "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al guardar los datos!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        });
    });
    
    $('#new').on('hidden.bs.modal', function () {
        cem.clearFormMantanedor();
    });

    $('#cursos').on('hidden.bs.modal', function () {
        cem.clearFormMantanedor('addCurso');
    });

    $('body').on('click', '.btnCursos', function () {
        cem.fillTableCursos($(this).attr('data-id'), $(this).attr('data-url'));
        $('#cursos').modal('show');
    });

    $('body').on('click', '.notaAlumnos', function () {
        cem.fillTableNotas($(this).attr('data-id'), $(this).attr('data-url'));
        $('#notas').modal('show');
    });

    $('body').on('click', '.editaCurso', function () {
        cem.fillInputMantenedor($(this).attr('data-id'), $(this).attr('data-url'), 'addCurso');
    });

    $('body').on('click', '.getCert', function () {
        cem.getCertificado($(this).attr('data-id'), $(this).attr('data-url'));
    });

    $('body').on('click', '.editaNota', function () {
        cem.fillInputMantenedor($(this).attr('data-id'), $(this).attr('data-url'), 'addNota');
        $('#saveNota').show();
    });

    $('body').on('click', '.btnEditar', function () {
        cem.fillInputMantenedor($(this).attr('data-id'), $(this).attr('data-url'));
        $('#new').modal('show');
    });

    $('body').on('click', '.btnImagen', function () {
        cem.clearFormMantanedor('addImage');
        $('#idInstancia').val($(this).attr('data-id'));
        if ($(this).attr('data-url')) {
            cem.fillInputMantenedor($(this).attr('data-id'), $(this).attr('data-url'), 'image');
        }
        $('#image').modal('show');
    });

    $('body').on('click', '.btnEliminar', function () {
        var id = $(this).attr('data-id');
        var url = $(this).attr('data-url');
        swal({
            title: 'Eliminar Registro',
            text: 'Está seguro que desea eliminar este registro?',
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: 'slide-from-top',
            showLoaderOnConfirm: true
        },
        function () {
            $.ajax({
                url: url,
                data: 'id=' + id,
                type: "POST",
                success: function (data) {
                    if (data.response === 1) {
                        swal({
                            title: "Registro eliminado!",
                            text: "",
                            type: "success"
                        },
                                function () {
                                    location.reload();
                                });
                    } else {
                        swal({
                            title: "Error al eliminar el registro!",
                            text: data.msg || "Intente nuevamente.",
                            type: "error"
                        });
                    }
                },
                error: function () {
                    swal({
                        title: "Error al eliminar el registro!",
                        text: "Intente nuevamente.",
                        type: "error"
                    });
                }
            });
        });
    });

    var cem = {
        dataFormMantenedor: function (form) {
            form = form || 'form';
            var data = 'q=q';
            $('.' + form).find('.form-control').each(function () {
                if ($(this).parent('[class*="icheckbox"]').length>0)
                    data += '&' + $(this).attr('name') + '=' + ($(this).parent('[class*="icheckbox"]').hasClass("checked")?1:0);
                else
                    data += '&' + $(this).attr('name') + '=' + $(this).val();
            });
            return data;
        },
        clearFormMantanedor: function (form) {
            form = form || 'addForm';
            $('#' + form).find('.form-control').each(function () {
                $(this).parents('.form-group').removeClass('has-error');
                if ($(this).is('select'))
                    $(this).val(null).trigger('change');
                else if ($(this).attr('type') === 'hidden')
                    $(this).val('0');
                else
                    $(this).val('');
            });
        },
        fillInputMantenedor: function(id, url, form) {
            form = form || 'addForm';
            $.ajax({
                url: url,
                data: 'id=' + id,
                type: "POST",
                success: function (data) {
                    if (data.response === 0) {
                        swal({
                            title: "Error al obtener datos!",
                            text: data.msg || "Intente nuevamente.",
                            type: "error"
                        });
                    } else {
                        $('#' + form).find('.form-control, .image').each(function () {
                            if ($(this).hasClass('image')) {
                                if (data.data.src !== undefined)
                                    $(this).html('<img src="' + data.data['src'] + '" class="img-responsive img-thumbnail"/>');
                                else
                                    $(this).html('<div class="alert alert-warning"><strong>Atención!</strong> EL Centro no registra una imagen.</div>');
                            } else if ($(this).is('select'))
                                $(this).select2('val', data.data[$(this).attr('name')]);
                            else
                                $(this).val(data.data[$(this).attr('name')]);
                        });
                    }
                }
            });
        },
        fillTableCursos: function(id, url, table) {
            table = table || 'table-cursos';
            $('#saveCurso').attr('data-programa', id);
            $.ajax({
                url: url,
                data: 'id=' + id,
                type: "POST",
                success: function (data) {
                    table = $('#' + table).children('tbody').text('');
                    
                    var row;
                    
                    for (var i in data) {
                        row = $('<tr><td>' + data[i].idCurso + '</td><td>' + data[i].nombreCurso + '</td><td>' + data[i].creditos + '</td><td>' + data[i].descripcion + '</td><td>' +
                            '<a class="btn btn-success notaAlumnos" data-url="/java.web/notas/lista.htm" data-toggle="tooltip" data-original-title="Notas Alumnos" data-id="' + data[i].idCurso + '">' +
                                '<i class="fa fa-bookmark"></i>' +
                            '</a> ' +
                            '<a class="btn btn-primary editaCurso" data-url="/java.web/cursos/editar.htm" data-toggle="tooltip" data-original-title="Editar" data-id="' + data[i].idCurso + '">' +
                                '<i class="fa fa-pencil-square-o"></i>' +
                            '</a> ' +
                            '<a class="btn btn-danger btnEliminar" data-url="/java.web/cursos/borrar.htm" data-toggle="tooltip" data-original-title="Eliminar" data-id="' + data[i].idCurso + '">' +
                                '<i class="fa fa-times-circle"></i>' +
                            '</a>' +
                        '</td></tr>');

                        table.append(row);
                    }
                }
            });
        },
        fillTableNotas: function(id, url, table) {
            table = table || 'table-notas';
            $.ajax({
                url: url,
                data: 'id=' + id,
                type: "POST",
                success: function (data) {
                    table = $('#' + table).children('tbody').text('');
                    
                    var row;
                    
                    for (var i in data) {
                        row = $('<tr><td>' + data[i].nombreAlumno + '</td><td>' + data[i].nota + '</td><td>' +
                            (data[i].nota >= 4? '<a class="btn btn-default getCert" data-url="/java.web/cursos/certificado.htm" data-toggle="tooltip" data-original-title="Certificado Aprobación" data-id="' + data[i].idAlumno + '">' +
                                '<i class="fa fa-file-pdf-o"></i>' +
                            '</a> ' : '') +
                            '<a class="btn btn-primary editaNota" data-url="/java.web/notas/editar.htm" data-toggle="tooltip" data-original-title="Editar" data-id="' + data[i].idNota + '">' +
                                '<i class="fa fa-pencil-square-o"></i>' +
                            '</a> ' +
                            /*'<a class="btn btn-danger btnEliminar" data-url="/java.web/notas/borrar.htm" data-toggle="tooltip" data-original-title="Eliminar" data-id="' + data[i].idNota + '">' +
                                '<i class="fa fa-times-circle"></i>' +
                            '</a>' +*/
                        '</td></tr>');

                        table.append(row);
                    }
                }
            });
        },
        getCertificado: function(id, url) {
            swal({
                title: 'Generar Certificado?',
                text: 'A continuación se generará el certificado...',
                type: "warning",
                showCancelButton: true,
                closeOnConfirm: false,
                animation: 'slide-from-top',
                showLoaderOnConfirm: true
            },
            function () {
                $.ajax({
                    url: url,
                    data: 'id=' + id,
                    type: "POST",
                    success: function (data) {
                        if (data.url !== undefined && data.url !== '')
                            swal({
                                title: "Certificado generado!",
                                text: 'Puede descargar su certificado <a href="' + data.url + '" target="_blank">aquí</a>',
                                type: "success",
                                html: "true"
                            });
                        else
                            swal({
                                title: "Error al generar certificado!",
                                text: "Intente nuevamente.",
                                type: "error"
                            });
                    }
                });
            });
        },
        validForm: function(form) {
            form = form || 'form';
            var valid = true;
            $('.' + form).find('.form-control').each(function () {
                if ($(this).attr('required') === 'required' || $(this).attr('required') === 'true') {
                    if ($(this).val() === '' || $(this).val() === null) {
                        $(this).parents('.form-group').addClass('has-error');
                        valid = false;
                    } else
                        $(this).parents('.form-group').removeClass('has-error');
                }
            });
            return valid;
        }
    };
    
    function formatMoney ( number, places, symbol, thousand, decimal ) {
        places = !isNaN(places = Math.abs(places)) ? places : 0;
        symbol = symbol !== undefined ? symbol : '$';
        thousand = thousand || '.';
        decimal = decimal || ',';
        var number = number,
            negative = number < 0 ? '-' : '',
            i = parseInt(number = Math.abs(+number || 0).toFixed(places), 10) + '',
            j = (j = i.length) > 3 ? j % 3 : 0;
        return symbol + ' ' + negative + (j ? i.substr(0, j) + thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, '$1' + thousand) + (places ? decimal + Math.abs(number - i).toFixed(places).slice(2) : '');
    };
    
    function validateEmail(email) {
        var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(email);
    }
    
    function openInNewTab(url) {
        $("<a>").attr("href", url).attr("target", "_blank")[0].click();
    }
}(jQuery));