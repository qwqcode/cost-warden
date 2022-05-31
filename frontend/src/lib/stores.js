import { writable } from 'svelte/store';
import * as Api from './api'

export const workspace = writable('add');
export const tags = writable([]);
export const selectedTid = writable(0);
export const costs = writable([]);
export const editCost = writable({});

export function SwitchWorkSpace(name) {
    if (name === 'add') editCost.set({});

    workspace.set(name)
}

export function FetchTags() {
    Api.getTags().then(rTags => {
        tags.set(rTags)
        selectedTid.set(rTags[0].tid)
    })
}

export function FetchCosts() {
    Api.getCosts().then((costs) => {
        updateCosts(costs)
    })
}

function updateCosts(src) {
    costs.set([]); // clear all original items
    const distArr = []
    src.forEach((c) => {
        // @sample "2022-05-30 16:02:21.322000000"
        const time = c.date.split(' ')[1].split('.')[0].substr(0, 5)
        const day = c.date.split(' ')[0].replace(/-/g, '/');
        c = { time, day, ...c }
        let dayArr = distArr.find(o => (o.day === day));
        if (!dayArr) {
            dayArr = { day, costs: [] }
            distArr.push(dayArr)
        }
        dayArr.costs.push(c);
    })
    costs.set(distArr) // notify arr changed
}

export function EditCost(costObj) {
    editCost.set(costObj)
}