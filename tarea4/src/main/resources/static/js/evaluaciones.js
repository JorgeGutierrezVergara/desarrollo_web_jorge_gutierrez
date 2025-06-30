document.addEventListener("DOMContentLoaded", () => {
  const actividadesTableBody = document.querySelector(
    "#actividadesTable tbody"
  );
  const noActivitiesMessage = document.getElementById("noActivitiesMessage");

  async function loadActividades() {
    try {
      const response = await fetch(
        "http://localhost:8080/api/actividades/realizadas"
      );

      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }

      const actividades = await response.json();

      actividadesTableBody.innerHTML = "";

      if (actividades.length === 0) {
        noActivitiesMessage.style.display = "block";
      } else {
        noActivitiesMessage.style.display = "none";
        actividades.forEach((actividad) => {
          const row = actividadesTableBody.insertRow();

          row.insertCell().textContent = actividad.id;
          row.insertCell().textContent = actividad.fechaInicio;
          row.insertCell().textContent = actividad.sector;
          row.insertCell().textContent = actividad.descripcion;
          const notaCell = row.insertCell();
          notaCell.textContent = actividad.nota;

          const actionCell = row.insertCell();
          actionCell.classList.add("action-cell");

          const renderEvaluarButton = (actId) => {
            actionCell.innerHTML = "";
            const evaluarBtn = document.createElement("button");
            evaluarBtn.textContent = "Evaluar";
            evaluarBtn.classList.add("evaluar-btn");
            evaluarBtn.dataset.actividadId = actId;
            actionCell.appendChild(evaluarBtn);

            evaluarBtn.addEventListener("click", () => {
              actionCell.innerHTML = "";

              const inputNota = document.createElement("input");
              inputNota.type = "number";
              inputNota.min = "1";
              inputNota.max = "7";
              inputNota.placeholder = "Nota (1-7)";
              inputNota.classList.add("nota-input");
              inputNota.value = "4";

              const sendBtn = document.createElement("button");
              sendBtn.textContent = "Enviar";
              sendBtn.classList.add("send-btn");
              sendBtn.classList.add("evaluar-btn");

              actionCell.appendChild(inputNota);
              actionCell.appendChild(sendBtn);

              sendBtn.addEventListener("click", async () => {
                const actividadId = evaluarBtn.dataset.actividadId;
                let nota = inputNota.value;

                nota = parseInt(nota);

                if (isNaN(nota) || nota < 1 || nota > 7) {
                  alert("Por favor, ingresa una nota válida entre 1 y 7.");
                  return;
                }

                try {
                  const response = await fetch(
                    `http://localhost:8080/api/actividades/${actividadId}/evaluar?nota=${nota}`,
                    {
                      method: "POST",
                      headers: {
                        "Content-Type": "application/json",
                      },
                    }
                  );

                  if (!response.ok) {
                    const errorText = await response.text();
                    throw new Error(
                      `Error del servidor al evaluar: ${errorText}`
                    );
                  }

                  const nuevoPromedio = await response.text();

                  notaCell.textContent = nuevoPromedio;

                  renderEvaluarButton(actividadId);
                } catch (error) {
                  console.error("Error al evaluar la actividad:", error);
                  alert(
                    `Ocurrió un error al evaluar la actividad: ${
                      error.message || error
                    }.`
                  );
                  renderEvaluarButton(actividadId);
                }
              });
            });
          };

          renderEvaluarButton(actividad.id);
        });
      }
    } catch (error) {
      console.error("Error al cargar actividades:", error);
      actividadesTableBody.innerHTML =
        '<tr><td colspan="6" style="text-align: center; color: red;">Error al cargar las actividades. Por favor, intente más tarde.</td></tr>';
      noActivitiesMessage.style.display = "none";
    }
  }

  loadActividades();
});
