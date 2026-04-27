const PLATEAU = [
                {
                    imageSrc:  'asset/Plateau/Case/0.png',
                    title:     'case + `X`',
                    id: 'plateau',
                    btnText:   'Voir Propriete',
                    btnClass:  'btn-casePlateau'
                },
                {
                    imageSrc:  'asset/Plateau/Case/1.png',
                    title:     'case + `X`',
                    id: 'plateau',
                    btnText:   'Voir Propriete',
                    btnClass:  'btn-casePlateau'
                }
                ];


/**
 * Injection dynamique dans le DOM
 */
function injecterContenuIndex(contenu) {
    const zonePlateau = document.getElementById('plateau');
    if (!zonePlateau) return;

    let html = PLATEAU.map(p => genererCardHTML(p)).join('');

    zonePlateau.innerHTML = html;

}

function genererCardHTML(plateauP) {
    return `
        <div class="case-app">
            <div class="case-image-box">
                <div class="case-image-fallback" style="background: linear-gradient(135deg,#1c2330,#2a3444);">
                    <img src="${plateauP.imageSrc}" 				
						alt="${plateauP.title}" 
				        title="Cliquez pour voir les détails"
				        onclick="afficherDetails('${plateauP.title}')" 
				        style="cursor: pointer;" 
				     >
                </div>
            </div>
            <div class="case-body">
                <h3>${plateauP.title}</h3>
                <button class="btn-case ${plateauP.btnClass}">${plateauP.btnText} →</button> </div>
        </div>
    `;
}

document.addEventListener('DOMContentLoaded', () => {
    injecterContenuIndex();
});

// La fonction qui sera appelée au clic
function afficherDetails(nom) {
    alert("Détails de la case : " + nom);
}