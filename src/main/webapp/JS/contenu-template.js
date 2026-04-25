const PLATEAU = [
                {
                    imageSrc:  '/asset/Plateau/Case/0.png',
                    title:     'case + `X`',
                    description:      'Case du plateau',
                    id: 'plateau',
                    btnText:   'Voir Propriete',
                    btnClass:  'btn-casePlateau'
                },
                {
                    imageSrc:  '/asset/Plateau/Case/1.png',
                    title:     'case + `X`',
                    description:      'Case du plateau',
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
                    <img src="${plateauP.imageSrc}" alt="${plateauP.title}" loading="lazy">
                </div>
            </div>
            <div class="case-body">
                <h3>${plateauP.title}</h3>
                <p>${plateauP.description}</p> <button class="btn-case ${plateauP.btnClass}">${plateauP.btnText} →</button> </div>
        </div>
    `;
}
